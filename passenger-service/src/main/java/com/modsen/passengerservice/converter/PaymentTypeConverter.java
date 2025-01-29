package com.modsen.passengerservice.converter;

import com.modsen.passengerservice.model.PaymentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class PaymentTypeConverter implements AttributeConverter<PaymentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentType paymentType) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.name().equals(paymentType.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + paymentType))
                .value();
    }

    @Override
    public PaymentType convertToEntityAttribute(Integer o) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.value() == o)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unknown attribute " + o));
    }
}
