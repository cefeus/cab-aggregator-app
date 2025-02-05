package com.modsen.rideservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum RideStatus {

    STARTED(0),
    IN_PROGRESS(1),
    FINISHED(2),
    DELETED(3);

    private final int code;

    public static Optional<PaymentType> fromCode(int code) {
        return Arrays.stream(PaymentType.values())
                .filter(s -> s.getCode() == code)
                .findAny();
    }

}
