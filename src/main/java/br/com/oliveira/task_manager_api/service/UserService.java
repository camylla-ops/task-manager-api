package br.com.oliveira.task_manager_api.service;

import br.com.oliveira.task_manager_api.dto.CreateUserDTO;
import br.com.oliveira.task_manager_api.dto.UserResponseDTO;
import br.com.oliveira.task_manager_api.entity.User;
import br.com.oliveira.task_manager_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(CreateUserDTO dto) {
        
        // 1. Verificar se o usuário já existe (pelo EMAIL, não username)
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está em uso!");
        }

        // 2. Converter DTO para Entity
        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setEmail(dto.getEmail());
        // (Sem senha por enquanto, conforme o tutorial novo)

        // 3. Salvar no Banco
        User savedUser = userRepository.save(newUser);

        // 4. Converter de volta para DTO de resposta
        return UserResponseDTO.fromEntity(savedUser);
    }
}