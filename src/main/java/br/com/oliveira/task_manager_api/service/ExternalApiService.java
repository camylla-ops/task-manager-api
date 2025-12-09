package br.com.oliveira.task_manager_api.service;

import br.com.oliveira.task_manager_api.dto.external.ExternalTaskResponse;
import br.com.oliveira.task_manager_api.dto.external.ExternalUsersResponse;
import br.com.oliveira.task_manager_api.entity.Task;
import br.com.oliveira.task_manager_api.entity.User;
import br.com.oliveira.task_manager_api.enums.TaskStatus;
import br.com.oliveira.task_manager_api.repository.TaskRepository;
import br.com.oliveira.task_manager_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final WebClient webClient;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public void seedDatabase() {
        System.out.println(" Iniciando população do banco de dados via API Externa ");

        
        // PARTE 1: BUSCAR E SALVAR USUÁRIOS
       
        // Vai na internet buscar os usuários
        var userResponse = webClient.get().uri("/users")
                .retrieve()
                .bodyToMono(ExternalUsersResponse.class)
                .block(); // Espera o download terminar

        if (userResponse != null && userResponse.getUsers() != null) {
            List<User> usersToSave = new ArrayList<>();
            
            for (var externalUser : userResponse.getUsers()) {
                User newUser = new User();
                // O ID a gente deixa o banco gerar (1, 2, 3...)
                newUser.setFirstName(externalUser.getFirstName());
                newUser.setLastName(externalUser.getLastName());
                newUser.setEmail(externalUser.getEmail());
                
                usersToSave.add(newUser);
            }
            // Salva todos os usuários de uma vez
            userRepository.saveAll(usersToSave);
            System.out.println("✅ Usuários salvos com sucesso!");
        }

        // ---------------------------------------------------------
        // PARTE 2: BUSCAR E SALVAR TAREFAS
        // ---------------------------------------------------------
        var taskResponse = webClient.get().uri("/todos")
                .retrieve()
                .bodyToMono(ExternalTaskResponse.class)
                .block();

        if (taskResponse != null && taskResponse.getTodos() != null) {
            List<Task> tasksToSave = new ArrayList<>();

            for (var externalTask : taskResponse.getTodos()) {
                Task newTask = new Task();
                
                // TRADUÇÃO DOS DADOS:
                // O site chama de 'todo', nós chamamos de 'title'
                newTask.setTitle(externalTask.getTodo()); 
                newTask.setDescription("Tarefa importada automaticamente");
                newTask.setDueDate(LocalDateTime.now().plusDays(7)); // Prazo de 7 dias
                newTask.setPublic(false);
                
                
                if (externalTask.isCompleted()) {
                    newTask.setStatus(TaskStatus.DONE);
                } else {
                    newTask.setStatus(TaskStatus.TODO);
                }

                // Tenta achar o dono dessa tarefa no nosso banco
                // (O ID 152 do site externo pode não existir no nosso banco H2 ainda, 
                // então só salvamos se acharmos um usuário compatível ou usamos o ID 1 como teste)
                // Aqui vamos tentar pegar pelo mesmo ID, assumindo que foram salvos na ordem.
                userRepository.findById(externalTask.getUserId()).ifPresent(owner -> {
                    newTask.setOwner(owner);
                    tasksToSave.add(newTask);
                });
            }
            
            if (!tasksToSave.isEmpty()) {
                taskRepository.saveAll(tasksToSave);
                System.out.println("✅ Tarefas salvas com sucesso!");
            }
        }
    }
}