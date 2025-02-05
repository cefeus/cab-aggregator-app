package com.modsen.rideservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum RideStatus {

    STARTED(0),
    IN_PROGRESS(100),
    FINISHED(200),
    DELETED(300);

    private final int code;

    public static Optional<PaymentType> fromCode(int code) {
        return Arrays.stream(PaymentType.values())
                .filter(s -> s.getCode() == code)
                .findAny();
    }

}
