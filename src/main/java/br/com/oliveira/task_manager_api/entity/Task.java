package br.com.oliveira.task_manager_api.entity;

import br.com.oliveira.task_manager_api.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500) // Permite descrições mais longas
    private String description;

    @Enumerated(EnumType.STRING) // Armazena o nome do enum no banco
    private TaskStatus status;

   
    @ManyToOne // Muitas Tarefas pertencem a Um Usuário
    @JoinColumn(name = "user_id", nullable = false) // Cria a coluna 'user_id' no banco
    private User user;
}