package br.com.oliveira.task_manager_api.service;

import br.com.oliveira.task_manager_api.dto.CreateUserDTO;
import br.com.oliveira.task_manager_api.dto.UserResponseDTO;
import br.com.oliveira.task_manager_api.entity.User;
import br.com.oliveira.task_manager_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Diz pro Spring que aqui tem Regra de Negócio
@RequiredArgsConstructor // O Lombok cria o construtor pra injetar o Repository
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(CreateUserDTO dto) {
        // 1. Verificar se o usuário já existe
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Este username já está em uso!");
        }

        // 2. Converter DTO (Caixa) para Entity (Objeto Real)
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword()); 
        // Nota: Aqui entra a criptografia depois!

        // 3. Salvar no Banco de Dados
        User savedUser = userRepository.save(newUser);

        // 4. Converter o salvo de volta para DTO (pra devolver pro usuário sem senha)
        return UserResponseDTO.fromEntity(savedUser);
    }
}