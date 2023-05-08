package com.nhnacademy.board.domain;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message = "userId is empty")
    private String userId;
    @NotEmpty(message = "userPassword is empty!")
    private String userPassword;

    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
}
