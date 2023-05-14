package com.nhnacademy.board.domain;

import lombok.Getter;

@Getter
public class UserEditDto {

    private String id;
    private String name;
    private String password;
    private String fileName;

    private UserEditDto() {
    }

    public UserEditDto(UserRequest userRequest, String fileName) {
        this.id = userRequest.getId();
        this.name = userRequest.getName();
        this.password = userRequest.getPassword();
        this.fileName = fileName;
    }
}
