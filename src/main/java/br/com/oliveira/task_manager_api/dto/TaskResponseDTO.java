package br.com.oliveira.task_manager_api.dto;

import java.time.LocalDateTime;
import br.com.oliveira.task_manager_api.entity.Task;
import br.com.oliveira.task_manager_api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private boolean isPublic;
    private String ownerName; 

    // Método que converte a Entidade Task para este DTO
    public static TaskResponseDTO fromEntity(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getStatus(),
            task.isPublic(),
            // Pega o nome do dono se existir, senão avisa
            task.getOwner() != null ? task.getOwner().getFirstName() : "Sem dono"
        );
    }
}