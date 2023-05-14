package com.nhnacademy.board.utils;

import com.nhnacademy.board.domain.UserRequest;
import com.nhnacademy.board.entity.User;

import java.util.Objects;

public class UserUtils {
    private UserUtils(){}

    public static User createByUserRequest(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setId(userRequest.getId());
        user.setPassword(userRequest.getPassword());
        if(Objects.nonNull(userRequest.getImage())){
            if(!Objects.equals(userRequest.getImage().getOriginalFilename(), "")){
                user.setProfileFileName(user.getId()+".jpeg");
            }
        }
        return user;
    }
}
