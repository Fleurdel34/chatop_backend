package com.example.chatop.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * @author johanna
 * @version 1.0.0
 */

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements UserDetails {

    /**
     * Build Class user
     * Set up properties (id, name, email(username), password, created_date, updated_date and role)
     * Implement constructor
     * @Getter and @Setter allows the implementation of getter and setter
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    private LocalDateTime created_date;

    private LocalDateTime updated_date;


    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+this.role.getRole()));
    }

}
