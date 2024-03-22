package com.redstevo.code.CustomExceptions;

public class EmailNotAvailableException extends RuntimeException {
    public EmailNotAvailableException(String message) {
        super(message);
    }
}
