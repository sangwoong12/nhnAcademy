package com.nhnacademy.springmvcstudent.service;

import com.nhnacademy.springmvcstudent.item.LoginRequest;
import com.nhnacademy.springmvcstudent.item.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean match(User user, LoginRequest loginRequest){
        if(user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword())){
            return true;
        }else {
            return false;
        }
    }
}
