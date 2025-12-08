package com.camylla.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Define como tabela do banco
@Table(name = "tb_users") // Nome personalizado da tabela
@Data // Gera Getters, Setters e toString (Lombok)
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String firstName;
    
    private String lastName;

    @Column(unique = true) // Não permite emails repetidos
    private String email;
    
}

