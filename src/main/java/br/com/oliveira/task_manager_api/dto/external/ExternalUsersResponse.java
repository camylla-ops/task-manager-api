package br.com.oliveira.task_manager_api.dto.external;
import lombok.Data;
import java.util.List;

@Data
public class ExternalUsersResponse {
    private List<ExternalUserDTO> users;
}