package com.team5.techradar.exception;

import com.team5.techradar.security.exception.EmailIsOccupiedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppError> handleResourceNotFoundException(BadCredentialsException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new AppError(
                        e.getMessage(),
                        HttpStatus.FORBIDDEN.value()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(EmailIsOccupiedException.class)
    public ResponseEntity<AppError> handleResourceNotFoundException(EmailIsOccupiedException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new AppError(
                        e.getMessage(),
                        HttpStatus.CONFLICT.value()
                ),
                HttpStatus.CONFLICT
        );
    }
}
