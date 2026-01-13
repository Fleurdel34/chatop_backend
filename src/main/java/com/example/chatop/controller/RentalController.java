package com.example.chatop.controller;


import com.example.chatop.dto.RentalDTO;
import com.example.chatop.pojo.Rental;
import com.example.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Tag(name = "Rentals", description = " rentals management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/rentals"})
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Operation(summary = "create rental")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "create rental, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping
    public ResponseEntity<Map<String, String>> createRental(@RequestBody Rental rental) {
        this.rentalService.createRental(rental);
        Map<String, String> response = Map.of(
                "message", "Rental created!"
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "findAll  rentals")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "find all rentals, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return this.rentalService.getRentalsAll();
    }

    @Operation(summary = "find one rental")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "find one rental by id, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable Long id) {
        return this.rentalService.getRentalById(id);
    }

    @Operation(summary = "update one rental")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "update one rentak, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PutMapping("/{id}")
    public Rental updateRentalById(@PathVariable Long id, @RequestBody Rental rental) {
        return this.rentalService.updateRentalById(id, rental);
    }

}
