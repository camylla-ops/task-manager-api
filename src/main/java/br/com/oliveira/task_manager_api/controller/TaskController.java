package br.com.oliveira.task_manager_api.controller;

import br.com.oliveira.task_manager_api.dto.CreateTaskDTO;
import br.com.oliveira.task_manager_api.dto.TaskResponseDTO;
import br.com.oliveira.task_manager_api.dto.UpdateStatusDTO;
import br.com.oliveira.task_manager_api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks") // A rota base é /tasks
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // POST http://localhost:8080/tasks?userId=1
    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(
            @RequestBody CreateTaskDTO request, 
            @RequestParam Long userId // Pegamos o ID do dono pela URL
    ) {
        
        // Chama o serviço mandando os dados da tarefa E o ID do dono
        var task = taskService.createTask(request, userId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }


    //  GET http://localhost:8080/tasks?userId=1
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> list(
            // Pega o ID do usuário que estamos consultando
            @RequestParam Long userId ) {
                
        // 1. Chama o Service para buscar a lista
        List<TaskResponseDTO> tasks = taskService.listTaskByUserId(userId);
        
        // 2. Retorna a lista com status 200 OK
        return ResponseEntity.ok(tasks);
    }
    
    // PATCH http://localhost:8080/tasks/{taskId}
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateStatus(
            @PathVariable Long taskId, // Pega o ID da tarefa na URL
            @Valid @RequestBody UpdateStatusDTO dto // Pega o status e o userId do corpo
    ) {
        // 1. Chama o Service para aplicar a lógica
        TaskResponseDTO updatedTask = taskService.updateTaskStatus(taskId, dto);
        
        // 2. Retorna 200 OK com a tarefa atualizada
        return ResponseEntity.ok(updatedTask);
    }

    // DELETE http://localhost:8080/tasks/{taskId}?userId=2
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 No Content se for sucesso
    public void delete(
        @PathVariable Long taskId, // Pega o ID da tarefa na URL
        @RequestParam Long userId  // Pega o ID do usuário que está deletando
    ) {
        // Chama o Service para executar a lógica de deleção
        taskService.deleteTask(taskId, userId);
    }
    
    // GET
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getById(
        @PathVariable Long taskId, // Pega o ID da tarefa da URL
        @RequestParam Long userId  // Pega o ID do usuário para checagem de visibilidade
    ) {
        // Chama o Service para buscar a tarefa com a validação de visibilidade
        TaskResponseDTO task = taskService.getTaskById(taskId, userId);
        
        return ResponseEntity.ok(task);
    }

    // ENDPOINT: PUT http://localhost:8080/tasks/{taskId}?userId=2
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> update(
        @PathVariable Long taskId, // ID da tarefa
        @RequestParam Long userId,  // ID do usuário para autorização
        @Valid @RequestBody CreateTaskDTO dto // Novos dados validados
    ) {
        TaskResponseDTO updatedTask = taskService.updateTask(taskId, dto, userId);
        return ResponseEntity.ok(updatedTask);
    }


}