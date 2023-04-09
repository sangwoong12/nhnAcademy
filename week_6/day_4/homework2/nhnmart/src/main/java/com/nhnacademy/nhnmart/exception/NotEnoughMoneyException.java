package com.nhnacademy.nhnmart.exception;

public class NotEnoughMoneyException extends RuntimeException {
    private static final String MESSAGE = "돈이 충분하지 않습니다.";

    public NotEnoughMoneyException() {
        super(MESSAGE);
    }
}
