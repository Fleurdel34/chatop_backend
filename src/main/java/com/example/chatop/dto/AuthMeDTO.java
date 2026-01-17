package com.example.chatop.dto;


import java.util.Date;

/**
 * Create Record to recover two params of Rental
 * @param id, name, email, created_at, updated-at
 */

public record AuthMeDTO(
        Long id,
        String name,
        String email,
        Date created_at,
        Date updated_at
) {
}
