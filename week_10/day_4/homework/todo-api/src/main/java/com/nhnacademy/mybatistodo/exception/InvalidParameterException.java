package com.nhnacademy.mybatistodo.exception;

public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException(String parameter) {
        super("Required request parameter " + parameter + " for method parameter type Integer is not present");
    }
}
