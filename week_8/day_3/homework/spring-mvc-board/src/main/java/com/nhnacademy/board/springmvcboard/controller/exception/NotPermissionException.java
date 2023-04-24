package com.nhnacademy.board.springmvcboard.controller.exception;

public class NotPermissionException extends RuntimeException{
    public NotPermissionException() {
        super("권한이 없습니다.");
    }
}
