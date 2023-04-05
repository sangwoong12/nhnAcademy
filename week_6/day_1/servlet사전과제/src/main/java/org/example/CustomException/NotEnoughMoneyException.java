package org.example.CustomException;

public class NotEnoughMoneyException extends RuntimeException{
    private static String MESSAGE = "돈이 충분하지 않습니다.";

    public NotEnoughMoneyException() {
        super(MESSAGE);
    }
}
