package br.com.oliveira.task_manager_api.repository;

import br.com.oliveira.task_manager_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Método Busca todas as tarefas onde o user.id é igual ao que passarmos
    List<Task> findByUserId(Long userId);
}