package com.redstevo.code.Models;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Getter
public class ExceptionModel {

    private String  message;

    private Date date;

    public ExceptionModel setMessage(String message) {
        this.message = message;
        return  this;
    }

    public ExceptionModel setDate(Date date) {
        this.date = date;
        return this;
    }

    public void build(){
        log.info("Model Ready For Response.");
    }
}
