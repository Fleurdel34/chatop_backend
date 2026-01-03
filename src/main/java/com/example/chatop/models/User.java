package com.example.chatop.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    private LocalDateTime created_date;

    private LocalDateTime updated_date;


    public User() {
    }

    public User(String name, String email, String password, LocalDateTime created_date, LocalDateTime updated_date) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }
}
