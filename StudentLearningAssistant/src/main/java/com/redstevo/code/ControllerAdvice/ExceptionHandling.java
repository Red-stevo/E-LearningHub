package com.redstevo.code.ControllerAdvice;

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

}
