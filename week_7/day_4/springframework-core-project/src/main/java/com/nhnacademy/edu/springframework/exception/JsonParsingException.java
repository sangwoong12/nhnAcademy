package com.nhnacademy.edu.springframework.exception;

public class JsonParsingException extends RuntimeException{
    private final static String MESSAGE = "JSON 파싱중 에러가 발생하였습니다.";

    public JsonParsingException() {
        super(MESSAGE);
    }
}
