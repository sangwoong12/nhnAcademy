package com.nhnacademy.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditPostRequest {
    long id;
    String title;
    String content;
}
