package br.com.oliveira.task_manager_api.controller;

import br.com.oliveira.task_manager_api.dto.CreateUserDTO;
import br.com.oliveira.task_manager_api.dto.UserResponseDTO;
import br.com.oliveira.task_manager_api.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // Define que a rota é http://localhost:8080/users
@RequiredArgsConstructor // Injeta o Service automaticamente
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserDTO request) {
        // Chama o Service para criar o usuário usando os DTOs
        var user = userService.createUser(request);
        
        // Retorna Status 201 (Created) e o JSON de resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // GET http://localhost:8080/users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        // 1. Chama o Service para buscar a lista de usuários
        List<UserResponseDTO> users = userService.listAllUsers();
        
        // 2. Retorna a lista com status 200 OK
        return ResponseEntity.ok(users);
    }
}