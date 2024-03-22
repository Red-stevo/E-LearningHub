package com.redstevo.code.CustomExceptions;

public class InvalidOTPException extends RuntimeException{
    public InvalidOTPException(String message) {
        super(message);
    }
}
