package com.example.chatop.repository;

import com.example.chatop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Build UserRepository
 * extends CrudRepository from tools Jpa
 * Create method findByUsername
 * @Params string username=>email
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
