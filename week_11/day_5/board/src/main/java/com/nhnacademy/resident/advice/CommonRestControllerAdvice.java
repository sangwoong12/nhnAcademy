package com.nhnacademy.resident.advice;

import com.nhnacademy.resident.domain.error.ErrorResponse;
import com.nhnacademy.resident.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.*;

@RestControllerAdvice
public class CommonRestControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.initDirectFieldAccess();
    }
    @ExceptionHandler(Exception.class) @OneToMany @ManyToOne @OneToOne
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage(), "500"));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(builder.toString(),"400"));
    }
}
