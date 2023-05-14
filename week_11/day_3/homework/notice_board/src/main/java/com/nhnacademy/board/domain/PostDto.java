package com.nhnacademy.board.domain;

public interface PostDto {
    Long getId();
    String getTitle();
    String getViewCount();

    User getUser();

    interface User {
        String getId();
    }
}
