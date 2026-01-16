package com.example.chatop.dto;

import java.util.Date;

/**
 * Create Record to recover two params of Rental
 * @param id, name, surface, price, picture, description, ownerId, created_date, updated-date
 */

public record RentalDTO(
        Long id,
        String name,
        Integer surface,
        Integer price,
        String picture,
        String description,
        Long owner_id,
        Date created_at,
        Date updated_at
) {
}
