package com.nhnacademy.springmvcstudent.exception;

public class NotFoundStudentException extends RuntimeException {
    private static final String MESSAGE = "해당 학생이 존재하지 않습니다.";

    public NotFoundStudentException() {
        super(MESSAGE);
    }
}
