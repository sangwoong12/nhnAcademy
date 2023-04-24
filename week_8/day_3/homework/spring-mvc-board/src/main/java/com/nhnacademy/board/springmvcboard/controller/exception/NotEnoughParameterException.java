package com.nhnacademy.board.springmvcboard.controller.exception;

public class NotEnoughParameterException extends RuntimeException{
    public NotEnoughParameterException() {
        super("필수 데이터가 NULL 입니다.");
    }
}
