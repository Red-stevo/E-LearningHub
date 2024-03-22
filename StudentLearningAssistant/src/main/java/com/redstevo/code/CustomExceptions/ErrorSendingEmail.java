package com.redstevo.code.CustomExceptions;

public class ErrorSendingEmail extends RuntimeException {
    public ErrorSendingEmail(String message) {
        super(message);
    }
}
