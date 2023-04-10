package com.nhnacademy.notice_board.exception;

public class NotFoundPostException extends RuntimeException{
    public NotFoundPostException() {
        super("존재하지 않는 게시글 입니다.");
    }
}
