package com.nhnacademy.springjpa.validator;

import com.nhnacademy.springjpa.domain.UserRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegisterRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest request = (UserRegisterRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "password cannot be null");
    }

}
