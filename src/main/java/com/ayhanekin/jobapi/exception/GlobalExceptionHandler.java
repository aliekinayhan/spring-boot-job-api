package com.ayhanekin.jobapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice // Global exception management point
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class) // If this class works call this method
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException exception,HttpServletRequest request){

        ApiErrorResponse response = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ErrorCode.RESOURCE_NOT_FOUND)
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
