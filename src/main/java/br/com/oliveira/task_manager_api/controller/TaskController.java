package br.com.oliveira.task_manager_api.controller;

import br.com.oliveira.task_manager_api.dto.CreateTaskDTO;
import br.com.oliveira.task_manager_api.dto.TaskResponseDTO;
import br.com.oliveira.task_manager_api.service.TaskService;
import lombok.RequiredArgsConstructor;
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
}