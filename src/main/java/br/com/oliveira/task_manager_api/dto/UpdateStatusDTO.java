package br.com.oliveira.task_manager_api.dto;

import br.com.oliveira.task_manager_api.enums.TaskStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class UpdateStatusDTO {
    
    // O status que queremos aplicar na tarefa
    @NotNull(message = "O status da tarefa é obrigatório.")
    private TaskStatus status;
    
    // O ID do usuário que está fazendo a requisição (usado para checagem de segurança)
    @NotNull(message = "O ID do usuário é obrigatório para a validação.")
    private Long userId; 
}