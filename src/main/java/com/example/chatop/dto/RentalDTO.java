package com.example.chatop.dto;

import java.time.LocalDateTime;

/**
 * Create Record to recover two params of Rental
 * @param rentalId, name, surface, price, picture, description, ownerId, created_date, updated-date
 */

public record RentalDTO(
        Long rentalId,
        String name,
        Integer surface,
        Integer price,
        String picture,
        String description,
        Long ownerId,
        LocalDateTime created_date,
        LocalDateTime updated_date
) {
}
