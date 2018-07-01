package com.company.store.controller;

import com.company.store.model.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SignUpValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        Pattern pattern = Pattern.compile("^((\\+380)(\\d{9}))$");
        Matcher matcher = pattern.matcher(user.getPhone());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "secondName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.empty");

        if (!matcher.matches()) {
            errors.rejectValue("phone", "phone.notValid");
        }
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "password.short");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            errors.rejectValue("email", "email.notValid");
        }
    }
}
