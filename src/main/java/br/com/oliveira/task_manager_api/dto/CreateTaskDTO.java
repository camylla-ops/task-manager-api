package br.com.oliveira.task_manager_api.dto;

import java.time.LocalDateTime;
import br.com.oliveira.task_manager_api.enums.TaskStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank; // NOVO
import jakarta.validation.constraints.NotNull; // NOVO

@Data
public class CreateTaskDTO {
    
    // O Título é obrigatório
    @NotBlank(message = "O título da tarefa é obrigatório.") 
    private String title;
    
    private String description;
    
    // A data de vencimento é obrigatória
    @NotNull(message = "A data de vencimento é obrigatória.") 
    private LocalDateTime dueDate;
    
    // O status inicial é obrigatório
    @NotNull(message = "O status inicial da tarefa é obrigatório.") 
    private TaskStatus status;
    
    private boolean isPublic;
}