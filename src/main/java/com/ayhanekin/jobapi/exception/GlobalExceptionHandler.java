package com.ayhanekin.jobapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Global exception management point
public class GlobalExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class) // If this class works call this method
    public ResponseEntity<ApiErrorResponse> handleJobNotFound(JobNotFoundException exception) {
        ApiErrorResponse error = new ApiErrorResponse(
                "Job Not Found",
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
