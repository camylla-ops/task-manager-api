package br.com.oliveira.task_manager_api.repository;

import br.com.oliveira.task_manager_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    @Query("SELECT t FROM Task t " +
       "LEFT JOIN t.participants p " + // FAZ UM JOIN com a lista de participantes (apelido 'p')
       "WHERE t.owner.id = :userId " +          
       "OR t.isPublic = true " +                
       "OR p.id = :userId " + // COMPARA o ID do usuário (p.id) com o ID passado (:userId)
       "GROUP BY t.id") // Garante que cada tarefa seja retornada apenas uma vez
    List<Task> findAccessibleTasksByUserId(@Param("userId") Long userId);

}