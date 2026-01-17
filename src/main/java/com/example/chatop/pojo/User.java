package com.example.chatop.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * @author johanna
 * @version 1.0.0
 */

@Getter
@Setter
@Entity
@Table(name = "user")
public  class User implements UserDetails {

    /**
     * Build Class user
     * Set up properties (id, name, email(username), password, created_at, updated_at and role)
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
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    private Date created_at;

    private Date updated_at;


    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+this.role.getRole()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }


}
