package org.example.CustomException;

public class NotFoundFoodException extends RuntimeException{
    private static String MESSAGE = "식품 매대에 존재하지 않는 음식입니다.";

    public NotFoundFoodException() {
        super(MESSAGE);
    }
}
