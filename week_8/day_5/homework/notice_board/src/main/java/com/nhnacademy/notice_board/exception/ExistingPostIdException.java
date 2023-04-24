package com.nhnacademy.notice_board.exception;

public class ExistingPostIdException extends RuntimeException {
    public ExistingPostIdException(long id) {
        super(id + "는 이미 존재하는 포스트 번호입니다.");
    }
}
