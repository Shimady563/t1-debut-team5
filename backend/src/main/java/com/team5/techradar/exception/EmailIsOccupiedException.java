package com.team5.techradar.exception;

public class EmailIsOccupiedException extends RuntimeException {
    public EmailIsOccupiedException(String message) {
        super(message);
    }
}
