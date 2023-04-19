package com.nhnacademy.springmvcstudent.exception;

public class DuplicateIdException extends RuntimeException {
    private static final String MESSAGE = "아이디가 존재합니다.";

    public DuplicateIdException() {
        super(MESSAGE);
    }
}
