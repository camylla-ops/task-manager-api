package br.com.oliveira.task_manager_api.repository; 
import br.com.oliveira.task_manager_api.entity.User; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca usuário pelo email
    Optional<User> findByEmail(String email);

}