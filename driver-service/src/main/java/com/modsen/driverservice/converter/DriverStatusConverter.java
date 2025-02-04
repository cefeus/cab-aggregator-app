package com.modsen.driverservice.converter;

import com.modsen.driverservice.exceptions.EnumConverterArgumentException;
import com.modsen.driverservice.model.enums.DriverStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.UNKNOWN_ARGUMENT;

@Converter
public class DriverStatusConverter implements AttributeConverter<DriverStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DriverStatus driverStatus) {
        return Arrays.stream(DriverStatus.values())
                .filter(p -> p.name().equals(driverStatus.name()))
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(driverStatus)))
                .getCode();
    }

    @Override
    public DriverStatus convertToEntityAttribute(Integer o) {
        return Arrays.stream(DriverStatus.values())
                .filter(p -> p.getCode() == o)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_ARGUMENT.formatted(o)));
    }
}
