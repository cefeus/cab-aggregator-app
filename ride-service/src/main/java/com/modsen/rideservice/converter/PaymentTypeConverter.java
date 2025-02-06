package com.modsen.rideservice.converter;

import com.modsen.rideservice.exceptions.EnumConverterArgumentException;
import com.modsen.rideservice.model.enums.PaymentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

import static com.modsen.rideservice.util.ExceptionMessagesConstants.UNKNOWN_ARGUMENT;

@Converter
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentType paymentType) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.name().equals(paymentType.name()))
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(paymentType)))
        .getCode();
    }

    @Override
    public PaymentType convertToEntityAttribute(Integer o) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.getCode() == o)
                .findAny()
                .orElseThrow(() -> new EnumConverterArgumentException(UNKNOWN_ARGUMENT.formatted(o)));
    }

}
