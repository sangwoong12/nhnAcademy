package com.nhnacademy.mybatistodo.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
        super("Unauthorized");
    }
}
