package com.nhnacademy.resident.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class CommonControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex){
        return ex.getMessage();
    }
}
