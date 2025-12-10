package br.com.oliveira.task_manager_api.repository;

import br.com.oliveira.task_manager_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Método Busca Tarefas por ID do Dono
    List<Task> findByOwnerId(Long ownerId);

    // Método Busca Tarefas por ID do Participante
    List<Task> findByParticipantsId(Long participantId);

    // Método Busca Tarefas onde o Usuário é Dono ou Participante
    List<Task> findByOwnerIdOrParticipantsId(Long ownerId, Long participantId);

    List<Task> findByOwnerIdOrIsPublicTrue(Long userId);

    // Método Busca Tarefas por Data de Vencimento
    List<Task> findByDueDate(LocalDateTime dueDate);


}