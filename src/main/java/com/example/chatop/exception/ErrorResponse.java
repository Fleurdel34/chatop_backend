package com.example.chatop.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private int Status;
    private String message;
    private List<String> details;
}
