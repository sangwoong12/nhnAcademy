package com.nhnacademy.mybatistodo.exception;

public class NotFoundEventOfEventAtException extends RuntimeException {
    public NotFoundEventOfEventAtException() {
        super("해당 날짜의 데이터가 존재하지 않아 삭제할수 없습니다.");
    }
}
