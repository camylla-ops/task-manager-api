package br.com.oliveira.task_manager_api.dto;

import br.com.oliveira.task_manager_api.entity.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;

    // metodo para converter Entity -> DTO rapidinho 
    public static UserResponseDTO fromEntity(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}