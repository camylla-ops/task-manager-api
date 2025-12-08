package br.com.oliveira.task_manager_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oliveira.task_manager_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    
    Optional<User> findByUsername(String username);
    
}
