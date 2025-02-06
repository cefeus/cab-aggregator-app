package com.modsen.rideservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum PaymentType {

    CASH(100),
    CARD(200);

    private final int code;

    public static Optional<PaymentType> fromCode(int code) {
        return Arrays.stream(PaymentType.values())
                .filter(s -> s.getCode() == code)
                .findAny();
    }
}
