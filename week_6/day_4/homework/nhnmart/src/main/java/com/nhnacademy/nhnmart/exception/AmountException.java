package com.nhnacademy.nhnmart.exception;

public class AmountException extends RuntimeException{
    private static final String MESSAGE = "상품의 수량이 부족합니다.";

    public AmountException() {
        super(MESSAGE);
    }
}
