package com.nhnacademy.notice_board.exception;

public class NotFoundIdException extends RuntimeException{
    public NotFoundIdException() {
        super("존재하지 않는 아이디입니다.");
    }
}
