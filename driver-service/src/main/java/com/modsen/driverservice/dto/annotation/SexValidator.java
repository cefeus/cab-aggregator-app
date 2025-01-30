package com.modsen.driverservice.dto.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class SexValidator implements ConstraintValidator<Sex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        return Arrays.stream(com.modsen.driverservice.model.enums.Sex.values())
                .anyMatch(sex -> sex.name().equals(s));
    }
}