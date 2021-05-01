package com.mycompany.basespringmvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber phoneNumber) {
    }

    @Override
    public boolean isValid(String phoneNumber,ConstraintValidatorContext cxt) {
        return phoneNumber == null || phoneNumber.matches("([+]?[0-9]{2,4}[\s-]?)?[0-9]{10}");
    }

}
