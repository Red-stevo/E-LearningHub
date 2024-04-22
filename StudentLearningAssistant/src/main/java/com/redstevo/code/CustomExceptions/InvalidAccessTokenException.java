package com.redstevo.code.CustomExceptions;

public class InvalidAccessTokenException extends RuntimeException{
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
