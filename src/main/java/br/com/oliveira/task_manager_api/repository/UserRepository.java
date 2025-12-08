package br.com.oliveira.task_manager_api.repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    
    Optional<User> findByUsername(String username);
    
}
