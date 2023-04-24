package com.example.todoapi.exception;

public class NotFoundEventException extends RuntimeException {

    public NotFoundEventException(long eventId) {
        super("이벤트가 존재하지 않습니다. eventId : " + eventId + "");
    }
}
