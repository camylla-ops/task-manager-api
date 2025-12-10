package br.com.oliveira.task_manager_api.service;

import br.com.oliveira.task_manager_api.dto.CreateTaskDTO;
import br.com.oliveira.task_manager_api.dto.TaskResponseDTO;
import br.com.oliveira.task_manager_api.dto.UpdateStatusDTO;
import br.com.oliveira.task_manager_api.entity.Task;
import br.com.oliveira.task_manager_api.entity.User;
import br.com.oliveira.task_manager_api.repository.TaskRepository;
import br.com.oliveira.task_manager_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository; 

    
    public TaskResponseDTO createTask(CreateTaskDTO dto, Long ownerId) {
        
        // 1. Buscar o Dono da Tarefa no Banco
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + ownerId));

        // 2. Converter DTO para Entity (Igual ao UserService)
        Task newTask = new Task();
        newTask.setTitle(dto.getTitle());
        newTask.setDescription(dto.getDescription());
        newTask.setDueDate(dto.getDueDate());
        newTask.setStatus(dto.getStatus());
        newTask.setPublic(dto.isPublic());
        
        // 3. Vincular o Dono à Tarefa (O passo extra!)
        newTask.setOwner(owner);

        // 4. Salvar no Banco
        Task savedTask = taskRepository.save(newTask);

        // 5. Converter de volta para DTO (Igual ao UserService)
        return TaskResponseDTO.fromEntity(savedTask);
    }


     public List<TaskResponseDTO> listTaskByUserId(long userId) {
       
    
        // 1. Busca no repositório todas as tarefas onde o usuário é Dono OU Participante.
        List<Task> tasks = taskRepository.findByOwnerIdOrParticipantsId(userId, userId);

        // 2. Mapeia (converte) a lista de Entidades (Task) para a lista de DTOs (TaskResponseDTO)
         return tasks.stream()
            .map(TaskResponseDTO::fromEntity) // Chame o método estático de conversão do DTO
            .collect(Collectors.toList());
    }

      public TaskResponseDTO updateTaskStatus(Long taskId, UpdateStatusDTO dto) {
    
    // 1. Busca a tarefa pelo ID ou lança erro 404
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada."));

    // 2. REGRA DE NEGÓCIO: Autorização (Só o dono pode mudar o status)
    if (!task.getOwner().getId().equals(dto.getUserId())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono pode atualizar o status desta tarefa.");
    }
    
    // 3. Atualiza o status
    task.setStatus(dto.getStatus());

    // 4. Salva no banco (o JPA atualiza o que já existe)
    Task updatedTask = taskRepository.save(task);

    // 5. Retorna o DTO de resposta
    return TaskResponseDTO.fromEntity(updatedTask);
   }

    public void deleteTask(Long taskId, Long userId) {
        // 1. Busca a tarefa ou lança erro 404
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada."));

        // 2. REGRA DE NEGÓCIO: Autorização (Só o dono pode deletar)
        if (!task.getOwner().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono pode deletar esta tarefa."); // Lança 403
        }

        // 3. Executar a remoção
        taskRepository.delete(task);
    } 

}