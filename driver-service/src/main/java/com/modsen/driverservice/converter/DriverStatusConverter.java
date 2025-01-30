package com.modsen.driverservice.converter;

import com.modsen.driverservice.model.enums.DriverStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class DriverStatusConverter implements AttributeConverter<DriverStatus, Integer> {


    @Override
    public Integer convertToDatabaseColumn(DriverStatus driverStatus) {
        return Arrays.stream(DriverStatus.values())
                .filter(p -> p.name().equals(driverStatus.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + driverStatus))
                .value();
    }

    @Override
    public DriverStatus convertToEntityAttribute(Integer o) {
        return Arrays.stream(DriverStatus.values())
                .filter(p -> p.value() == o)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + o));
    }
}
