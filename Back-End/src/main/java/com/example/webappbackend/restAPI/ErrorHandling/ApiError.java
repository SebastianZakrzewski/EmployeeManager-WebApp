package com.example.webappbackend.restAPI.ErrorHandling;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subError;
    private ApiError() {
        timestamp = LocalDateTime.now();
    }
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
