package com.example.chatop.controller;

import com.example.chatop.exception.RequestException;
import com.example.chatop.pojo.Message;
import com.example.chatop.pojo.Rental;
import com.example.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Message", description = " messages management")
@AllArgsConstructor
@RestController
@RequestMapping({"/api/messages"})
public class MessageController {

    @Autowired
    MessageService messageService;

    @Operation(summary = "create message")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "create messsage, authorized with JWTToken"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping
    public ResponseEntity<Map<String, String>> createMessage(@RequestBody Message message) throws RequestException {

        this.messageService.createMessage(message);
        Map<String, String> response = Map.of(
                "message", "Message send with success"
        );
        return ResponseEntity.ok(response);
    }
}
