package com.modsen.driverservice.dto.annotation;

import com.modsen.driverservice.model.enums.Sex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class SexValidator implements ConstraintValidator<ValidSex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        return Arrays.stream(Sex.values())
                .anyMatch(sex -> sex.name().equals(s));
    }
}