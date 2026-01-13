package com.example.chatop.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Build Class Rental
 * Set up properties (id , name, surface, price, picture, description, created_date, updated_date and join column id User)
 * @Getter and @Setter allows the implementation of getter and setter
 */


@Getter
@Setter
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long rentalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer surface;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    private LocalDateTime created_date;

    private LocalDateTime updated_date;


    public Rental() {
    }

    public Rental(String name, Integer surface, Integer price, String picture, String description, User user, LocalDateTime created_date, LocalDateTime updated_date) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.user = user;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }
}
