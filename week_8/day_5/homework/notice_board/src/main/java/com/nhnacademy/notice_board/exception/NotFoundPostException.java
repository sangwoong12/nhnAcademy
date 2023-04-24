package com.nhnacademy.notice_board.exception;

public class NotFoundPostException extends RuntimeException {
    public NotFoundPostException(long id) {
        super(id + "는 존재하지 않는 포스트 번호입니다.");
    }
}
