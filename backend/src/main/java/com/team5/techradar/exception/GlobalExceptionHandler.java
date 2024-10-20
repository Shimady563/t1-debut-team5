package com.team5.techradar.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new AppError(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<AppError> handleDataAccessException(DataAccessException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new AppError(
                        buildDataExceptionMessage(e.getMostSpecificCause().getMessage()),
                        HttpStatus.CONFLICT.value()
                ),
                HttpStatus.CONFLICT
        );
    }

    private String buildDataExceptionMessage(String message) {
        if (message.contains("Detail:")) {
            return message.split("Detail:")[1].trim();
        }
        return message;
    }
}
