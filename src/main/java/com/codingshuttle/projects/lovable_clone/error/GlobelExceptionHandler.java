package com.codingshuttle.projects.lovable_clone.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobelExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException e){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        log.error(apiError.toString(),e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getResourceName()+" with id "+e.getResourceId()+" not found");
        log.error(apiError.toString(),e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ApiFieldError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error-> new ApiFieldError(error.getField(),error.getDefaultMessage()))
                .toList();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Input Validations Failed", errors);
        log.error(apiError.toString(), e);
        return ResponseEntity.status(apiError.status()).body(apiError);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException e) {
        // 1. Add your custom logging here
        log.error("Unauthorized access attempt: User is not authorized to perform this action.", e);

        // 2. Create a custom response
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "User is not authorized to update this project");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }
}
