package com.nhnacademy.notice_board.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String id) {
        super(id + "는 존재하지 않는 유저 아이디입니다.");
    }
}
