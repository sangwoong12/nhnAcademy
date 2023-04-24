package com.nhnacademy.board.springmvcboard.item.user;

public interface User {
    String getId();
    void setId(String id);

    String getPassword();
    void setPassword(String password);

    String getName();
    void setName(String name);

    String getProfileFileName();
    void setProfileFileName(String profileFileName);
    Auth getAuth();
}
