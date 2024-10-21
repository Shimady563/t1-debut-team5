package com.team5.techradar.security.exception;

public class EmailIsOccupiedException extends Exception {
    public EmailIsOccupiedException() {
        super("Email is occupied");
    }
}
