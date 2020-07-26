package com.example.demo.entity;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if(user.getUsername().equals("Trung")){
            errors.rejectValue("username",null,"Rieng anh ko dang ky dc");
        }
    }
}
