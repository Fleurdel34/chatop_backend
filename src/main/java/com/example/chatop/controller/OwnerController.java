package com.example.chatop.controller;

import com.example.chatop.dto.OwnerDTO;
import com.example.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Owner", description = " owner management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/user"})
public class OwnerController {

    @Autowired
    UserService userService;

    @Operation(summary = "find one owner/user")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "find one owner/user by id, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getRentalById(@PathVariable Long id) {
        OwnerDTO ownerDTO = this.userService.getUserById(id);
        return new ResponseEntity<>(ownerDTO, HttpStatus.OK);
    }
}
