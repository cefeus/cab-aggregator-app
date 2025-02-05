package com.modsen.rideservice.converter;

import com.modsen.rideservice.exceptions.EnumConverterArgumentException;
import com.modsen.rideservice.model.enums.RideStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import static com.modsen.rideservice.util.ExceptionMessagesConstants.UNKNOWN_ARGUMENT;

@Converter
public class RideStatusConverter implements AttributeConverter<RideStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RideStatus rideStatus) {
        return Arrays.stream(RideStatus.values())
                .filter(p -> p.name().equals(rideStatus.name()))
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(rideStatus)))
                .getCode();
    }

    @Override
    public RideStatus convertToEntityAttribute(Integer o) {
        return Arrays.stream(RideStatus.values())
                .filter(p -> p.getCode() == o)
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(o)));
    }

}
