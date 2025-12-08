package br.com.oliveira.task_manager_api.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
}