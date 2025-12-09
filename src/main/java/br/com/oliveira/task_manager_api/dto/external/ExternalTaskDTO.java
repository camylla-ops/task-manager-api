package br.com.oliveira.task_manager_api.dto.external;

import lombok.Data;

@Data
public class ExternalTaskDTO {
    // Esses campos têm que ser IGUAIS ao que vem do site dummyjson.com/todos
    private Long id;
    private String todo;       
    private boolean completed; 
    private Long userId;      
}