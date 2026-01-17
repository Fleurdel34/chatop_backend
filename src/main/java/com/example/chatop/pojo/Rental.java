package com.example.chatop.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/**
 * Build Class Rental
 * Set up properties (id , name, surface, price, picture, description, created_at, updated_at and join column id User)
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
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer surface;

    @Column(nullable = false)
    private Integer price;

    @Lob
    @Column(nullable= false , columnDefinition = "LONGTEXT")
    private String picture;

    @Column(nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    private Date created_at;

    private Date updated_at;


    public Rental() {
    }

    public Rental(String name, Integer surface, Integer price, String picture, String description, User user, Date created_at, Date updated_at) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.user = user;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
