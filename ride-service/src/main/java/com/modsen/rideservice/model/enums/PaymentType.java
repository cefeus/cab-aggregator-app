package com.modsen.rideservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum PaymentType {

    CASH(1),
    CARD(2);

    private final int code;

    public static Optional<PaymentType> fromCode(int code) {
        return Arrays.stream(PaymentType.values())
                .filter(s -> s.getCode() == code)
                .findAny();
    }
}
