package com.example.todoapi.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("잘못된 이벤트 소유자");
    }
}
