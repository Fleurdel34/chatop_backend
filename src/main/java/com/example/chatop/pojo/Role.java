package com.example.chatop.pojo;

import com.example.chatop.enumeration.TypeRole;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Build Class Role
 * Set up properties (id and Enumeration)
 * @Data allows the implementation of constructor, getter and setter
 */

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @Enumerated(EnumType.STRING)
    private TypeRole role;

    public Role() {

    }
}
