package com.nhnacademy.springmvcstudent.validator;

import com.nhnacademy.springmvcstudent.item.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest loginRequest = (LoginRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required.userId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "required.userPassword");

        if(loginRequest.getUserPassword().isEmpty()){
            errors.rejectValue("userPassword","required.userPassword");
        }
        if(loginRequest.getUserId().isEmpty()){
            errors.rejectValue("userId","required.userId");
        }
    }
}