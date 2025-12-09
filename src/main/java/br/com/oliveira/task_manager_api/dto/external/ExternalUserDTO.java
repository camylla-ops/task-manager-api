package br.com.oliveira.task_manager_api.dto.external;
import lombok.Data;

@Data
public class ExternalUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}