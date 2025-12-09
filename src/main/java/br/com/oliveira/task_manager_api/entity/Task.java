package br.com.oliveira.task_manager_api.entity;

import java.util.ArrayList;
import java.util.List;

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
    private TaskStatus status; // to do, doing, done

   
    @ManyToOne // Muitas Tarefas pertencem a Um Usuário
    @JoinColumn (name = "owner_id")
    private User owner;

    @ManyToMany // Muitas Tarefas podem ter Muitos Colaboradores
    @JoinTable(

        name = "task_participants",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
        private List<User> participants = new ArrayList<>();
    


}