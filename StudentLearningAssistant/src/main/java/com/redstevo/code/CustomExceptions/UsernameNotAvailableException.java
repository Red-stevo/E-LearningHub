package com.redstevo.code.CustomExceptions;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException(String message) {
        super(message);
    }
}
