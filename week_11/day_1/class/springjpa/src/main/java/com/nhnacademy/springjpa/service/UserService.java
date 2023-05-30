package com.nhnacademy.springjpa.service;


import com.nhnacademy.springjpa.entity.User;

public interface UserService {
    User getUser(String id);

    User createUser(String id, String password);

    User modifyUser(User user);

}
