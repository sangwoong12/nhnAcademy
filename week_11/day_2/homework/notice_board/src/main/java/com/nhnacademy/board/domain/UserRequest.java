package com.nhnacademy.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String id;
    private String name;
    private String password;
    private MultipartFile image;

}
