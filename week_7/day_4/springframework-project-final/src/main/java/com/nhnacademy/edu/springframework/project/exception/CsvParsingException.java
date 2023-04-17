package com.nhnacademy.edu.springframework.project.exception;

public class CsvParsingException extends RuntimeException{
    private static final String MESSAGE = "CSV 파싱중 에러가 발생하였습니다.";

    public CsvParsingException() {
        super(MESSAGE);
    }
}
