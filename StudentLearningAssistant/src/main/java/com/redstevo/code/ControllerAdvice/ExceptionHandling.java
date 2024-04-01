package com.redstevo.code.ControllerAdvice;

import com.redstevo.code.CustomExceptions.*;
import com.redstevo.code.Models.ExceptionModel;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandling {

    private ExceptionModel exceptionModel;

    @PostConstruct
    public void injectObject(){
        exceptionModel = new ExceptionModel();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.info("Validation exception occurred.");

        List<FieldError> fieldError = e.getBindingResult().getFieldErrors();

        exceptionModel
                .setMessage(fieldError.getLast().getDefaultMessage())
                .setDate(new Date()).build();

        return new ResponseEntity<>(exceptionModel,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotAvailableException.class)
    public ResponseEntity<ExceptionModel> handleUsernameNameNotAvailableException(UsernameNotAvailableException e){
        log.error("User Not Found Exception");

        setModel(e.getMessage());
        return new ResponseEntity<>(exceptionModel,HttpStatus.BAD_REQUEST);
    }

    private void setModel(String e) {
        exceptionModel
                .setDate(new Date())
                .setMessage(e)
                .build();
        log.info("exception handled.");
    }

    @ExceptionHandler(EmailNotAvailableException.class)
    public ResponseEntity<ExceptionModel> handleEmailNotAvailableException(EmailNotAvailableException e){
        log.error("EmailNotAvailableException");

        setModel(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorSendingEmail.class)
    public ResponseEntity<ExceptionModel> handleErrorSendingEmail(ErrorSendingEmail e){
        log.error("ErrorSendingEmail");

        setModel(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ExceptionModel> handleImageNotFoundException(){
        log.error("ImageNotFoundException");

        setModel(exceptionModel.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<ExceptionModel> handleInvalidOTPException(InvalidOTPException e){
        log.error("InvalidOTPException");

        setModel(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionModel> handle(InvalidRequestException e){
        log.error("InvalidRequestException");

        setModel(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ExceptionModel> handleUserDoesNotExistException(UserDoesNotExistException e){
        log.error("UserDoesNotExistException");

        setModel(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }


}
