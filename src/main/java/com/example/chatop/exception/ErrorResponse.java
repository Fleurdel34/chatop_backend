package com.example.chatop.exception;

import lombok.Data;

import java.util.List;

/**
 * Create class ErrorResponse
 * Use @Data to generate constructor, setter and getter
 */


@Data
public class ErrorResponse {
    private int Status;
    private String message;
    private List<String> details;
}
