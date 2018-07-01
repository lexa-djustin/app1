package com.app1.example.Validator;

import com.app1.example.Entity.User;
import com.app1.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass)
    {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object entity, Errors errors)
    {
        User user = (User) entity;

        ValidationUtils.rejectIfEmpty(errors, "login", "Required");
        ValidationUtils.rejectIfEmpty(errors, "password", "Required");
    }
}
