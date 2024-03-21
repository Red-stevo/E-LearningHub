package com.redstevo.code.CustomExceptions;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
