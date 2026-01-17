package com.example.chatop.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Create class RequestException
 * Execute exception processing
 * Use enum
 */

@Getter
@Setter
public class RequestException extends Exception{

    @Getter
    public enum ErrorRequest{
        BAD_REQUEST_EXCEPTION("Bad request", 400),
        UNAUTHORIZED_EXCEPTION("Unauthorized", 401);

        private final String message;
        private final int code;

        ErrorRequest(String message, int code){
            this.message =message;
            this.code= code;
        }

    }

    private ErrorRequest errorRequest;
    private String message;

    public RequestException(ErrorRequest errorRequest, String message){
        this.errorRequest = errorRequest;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
