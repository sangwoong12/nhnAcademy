package com.nhnacademy.resident.entity;

public enum BirthDeathType {
    BIRTH("Birth"),
    DEATH("Death");

    private String code;

    BirthDeathType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
