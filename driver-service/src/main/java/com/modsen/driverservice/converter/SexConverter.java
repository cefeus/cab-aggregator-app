package com.modsen.driverservice.converter;

import com.modsen.driverservice.exceptions.EnumConverterArgumentException;
import com.modsen.driverservice.model.enums.Sex;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.UNKNOWN_ARGUMENT;

@Converter
public class SexConverter implements AttributeConverter<Sex, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Sex sex) {
        return Arrays.stream(Sex.values())
                .filter(p -> p.name().equals(sex.name()))
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(sex)))
                .getCode();
    }

    @Override
    public Sex convertToEntityAttribute(Integer o) {
        return Arrays.stream(Sex.values())
                .filter(p -> p.getCode() == o)
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(o)));
    }
}
