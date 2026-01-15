package com.example.chatop.repository;

import com.example.chatop.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Build MessageRepository
 * extends CrudRepository from tools Jpa
 * @Params Long id
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
