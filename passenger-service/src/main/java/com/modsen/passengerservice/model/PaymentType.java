package com.modsen.passengerservice.model;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentType {

    CARD(1), CASH(2);

    private final int paymentCode;

    PaymentType(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public static Optional<PaymentType> castIntToPaymentType(int paymentCode) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.value() == paymentCode)
                .findAny();
    }

    public int value() {
        return this.paymentCode;
    }
}
