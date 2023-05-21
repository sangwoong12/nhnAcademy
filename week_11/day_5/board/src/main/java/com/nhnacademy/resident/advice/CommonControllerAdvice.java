package com.nhnacademy.resident.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex){
        ex.printStackTrace();
        return null;
    }
}
