package com.redstevo.code.ControllerAdvice;

import com.redstevo.code.CustomExceptions.*;
import com.redstevo.code.Models.ExceptionModel;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionModel> handleSignatureException(){
        log.error("SignatureException");

        setModel("Illegal Token Modification");

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public  ResponseEntity<ExceptionModel> handleMalformedJwtException(){
        log.error("MalformedJwtException");

        setModel("Illegal access token Modification.");

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidRefreshToken.class)
    public ResponseEntity<ExceptionModel> handleInvalidRefreshToken(InvalidRefreshToken e){
        log.error("InvalidRefreshToken");

        setModel(e.getMessage());

        return  new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionModel> handleExpiredJwtException(){
        setModel("Expired Access Token");

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionModel> handleBadCredentialsException(){
        setModel("Incorrect password");

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ExceptionModel> handleInternalAuthenticationServiceException(){
        setModel("Incorrect user name");

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidAccessTokenException.class)
    public ResponseEntity<ExceptionModel> handleInvalidAccessTokenException(InvalidAccessTokenException e){
        exceptionModel.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ResponseEntity<ExceptionModel> handleUsernameNotFoundException(UsernameNotFoundException e){
        log.error("UsernameNotFoundException");

        setModel(e.getMessage());

        return  new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    /*Fallback exception handler*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handleException(Exception e){

        log.error(e.getClass().toString());
        exceptionModel.setMessage("Internal Server Error");

        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
