package com.nhnacademy.notice_board.exception;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException() {
        super("존재하지 않는 유저입니다.");
    }
}
