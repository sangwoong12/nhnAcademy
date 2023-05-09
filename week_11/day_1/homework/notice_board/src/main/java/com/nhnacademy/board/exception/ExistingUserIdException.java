package com.nhnacademy.board.exception;

public class ExistingUserIdException extends RuntimeException {
    public ExistingUserIdException(String id) {
        super(id + "는 이미 존재하는 아이디입니다.");
    }
}
