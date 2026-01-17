package com.example.chatop.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Build Class message
 * Set up properties (id, message, user_id and rental_id)
 * Implement constructor
 * @Getter and @Setter allows the implementation of getter and setter
 */

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private Long rental_id;

    public Message(String message, Long user_id, Long rental_id) {
        this.message = message;
        this.user_id = user_id;
        this.rental_id = rental_id;
    }

    public Message() {
    }


}
