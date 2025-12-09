package br.com.oliveira.task_manager_api.service;

import br.com.oliveira.task_manager_api.dto.CreateUserDTO;
import br.com.oliveira.task_manager_api.dto.UserResponseDTO;
import br.com.oliveira.task_manager_api.entity.User;
import br.com.oliveira.task_manager_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(CreateUserDTO dto) {
        
        // 1. Verificar se o usuário já existe 
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está em uso!");
        }

        // 2. Converter DTO para Entity
        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setEmail(dto.getEmail());
        // (Sem senha por enquanto)

        // 3. Salvar no Banco
        User savedUser = userRepository.save(newUser);

        // 4. Converter de volta para DTO de resposta
        return UserResponseDTO.fromEntity(savedUser);
    }

    public List<UserResponseDTO> listAllUsers() {
        // 1. Usa o método padrão do JpaRepository para buscar todos
        List<User> users = userRepository.findAll();
        
        // 2. Converte a lista de Entidades (User) para a lista de DTOs (UserResponseDTO)
        return users.stream()
                .map(UserResponseDTO::fromEntity) 
                .collect(Collectors.toList());
    }
}