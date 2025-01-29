package com.modsen.passengerservice.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public enum PaymentType {

    CARD(1),
    CASH(2);

    private final int paymentCode;

    public static Optional<PaymentType> fromCode(int paymentCode) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.value() == paymentCode)
                .findAny();
    }

    public int value() {
        return this.paymentCode;
    }
}
