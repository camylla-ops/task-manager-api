package br.com.oliveira.task_manager_api.dto;

import java.time.LocalDateTime;
import br.com.oliveira.task_manager_api.enums.TaskStatus;
import lombok.Data;

@Data
public class CreateTaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private boolean isPublic;
}