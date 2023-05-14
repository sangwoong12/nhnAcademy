package com.nhnacademy.board.exception;

public class PermissionException extends RuntimeException{
    public PermissionException() {
        super("해당 행위를 수행할 권한이 없습니다.");
    }
}
