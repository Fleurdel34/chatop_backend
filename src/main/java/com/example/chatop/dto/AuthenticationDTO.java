package com.example.chatop.dto;

/**
 * Create Record to recover two params of User
 * @param email
 * @param password
 */

public record AuthenticationDTO(String email, String password) {
}
