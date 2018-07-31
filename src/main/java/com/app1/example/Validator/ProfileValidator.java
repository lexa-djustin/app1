package com.app1.example.Validator;

import com.app1.example.Entity.Profile;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        Profile profile = (Profile) target;

        System.out.println(profile.getName());

        ValidationUtils.rejectIfEmpty(e, "name", "Name is empty");
    }
}