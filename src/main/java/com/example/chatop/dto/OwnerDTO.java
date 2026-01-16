package com.example.chatop.dto;

import java.util.Date;

public record OwnerDTO(
        Long id,
        String name,
        String email,
        Date created_at,
        Date updated_at
) {
}
