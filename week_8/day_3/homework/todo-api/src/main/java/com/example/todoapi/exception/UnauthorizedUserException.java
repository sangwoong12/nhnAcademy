package com.example.todoapi.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
        super("Unauthorized");
    }
}
