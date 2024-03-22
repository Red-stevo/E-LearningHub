package com.redstevo.code.CustomExceptions;

public class UsernameNameNotAvailableException extends RuntimeException {
    public UsernameNameNotAvailableException(String message) {
        super(message);
    }
}
