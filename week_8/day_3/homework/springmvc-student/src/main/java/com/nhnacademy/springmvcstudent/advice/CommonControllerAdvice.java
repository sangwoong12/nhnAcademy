package com.nhnacademy.springmvcstudent.advice;

import com.nhnacademy.springmvcstudent.exception.DuplicateIdException;
import com.nhnacademy.springmvcstudent.exception.NotFoundStudentException;
import com.nhnacademy.springmvcstudent.exception.ValidException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.initDirectFieldAccess();
    }

    @ExceptionHandler(NotFoundStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundStudentException(Model model, Exception ex) {
        model.addAttribute("message", ex);
        return "error/error";
    }

    @ExceptionHandler(ValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidException(Model model, Exception ex) {
        model.addAttribute("message", ex);
        return "error/error";
    }

    @ExceptionHandler(DuplicateIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateIdException(Model model, Exception ex) {
        model.addAttribute("message", ex);
        return "error/error";
    }
}
