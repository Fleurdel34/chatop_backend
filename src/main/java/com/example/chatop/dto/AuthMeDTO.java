package com.example.chatop.dto;

import java.time.LocalDateTime;

public record AuthMeDTO(
        Long Id,
        String name,
        String email,
        LocalDateTime created_date,
        LocalDateTime updated_date
) {
}
