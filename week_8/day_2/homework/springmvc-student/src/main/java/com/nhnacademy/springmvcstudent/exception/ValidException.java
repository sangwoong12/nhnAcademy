package com.nhnacademy.springmvcstudent.exception;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidException extends RuntimeException {
    public ValidException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                        .append(",Message=").append(error.getDefaultMessage())
                        .append(",code=").append(error.getCode()))
                .collect(Collectors.joining(" | ")));
    }
}
