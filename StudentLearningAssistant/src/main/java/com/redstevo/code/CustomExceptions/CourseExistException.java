package com.redstevo.code.CustomExceptions;

public class CourseExistException extends RuntimeException{
    public CourseExistException(String message) {
        super(message);
    }
}
