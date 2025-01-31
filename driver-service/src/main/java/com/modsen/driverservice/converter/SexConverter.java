package com.modsen.driverservice.converter;

import com.modsen.driverservice.model.enums.Sex;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class SexConverter implements AttributeConverter<Sex, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Sex sex) {
        return Arrays.stream(Sex.values())
                .filter(p -> p.name().equals(sex.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + sex))
                .value();
    }

    @Override
    public Sex convertToEntityAttribute(Integer o) {
        return Arrays.stream(Sex.values())
                .filter(p -> p.value() == o)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + o));
    }
}
