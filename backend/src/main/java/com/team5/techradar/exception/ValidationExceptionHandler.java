package com.team5.techradar.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> handleConstraintViolationException(ConstraintViolationException e) {
        List<ValidationError.Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new ValidationError.Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                )).toList();
        log.error("Validation error: {}", e.getMessage());
        return new ResponseEntity<>(
                new ValidationError(
                        violations,
                        HttpStatus.BAD_REQUEST.value(),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError.Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError.Violation(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList();
        log.error("Validation error: {}", e.getMessage());
        return new ResponseEntity<>(
                new ValidationError(
                        violations,
                        HttpStatus.BAD_REQUEST.value(),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
