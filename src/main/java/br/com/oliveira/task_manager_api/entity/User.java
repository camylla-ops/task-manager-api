package br.com.oliveira.task_manager_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Diz pro Spring que isso é uma tabela no banco
@Table(name = "tb_users") // Define o nome da tabela no banco (plural)
@Data // O Lombok cria os Getters, Setters e toString sozinho

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
}
