package com.redstevo.code.CustomExceptions;

import org.springframework.stereotype.Component;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
