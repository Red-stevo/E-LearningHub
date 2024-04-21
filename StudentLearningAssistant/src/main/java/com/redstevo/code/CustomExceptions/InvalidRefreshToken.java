package com.redstevo.code.CustomExceptions;

public class InvalidRefreshToken extends RuntimeException{
    public InvalidRefreshToken(String message) {
        super(message);
    }
}
