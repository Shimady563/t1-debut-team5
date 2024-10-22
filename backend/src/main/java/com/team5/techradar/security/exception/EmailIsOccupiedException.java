package com.team5.techradar.security.exception;

public class EmailIsOccupiedException extends RuntimeException {
    public EmailIsOccupiedException(String message) {
        super(message);
    }
}
