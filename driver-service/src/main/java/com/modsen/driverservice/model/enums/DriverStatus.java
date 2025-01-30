package com.modsen.driverservice.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public enum DriverStatus {
    CREATED(1),
    NO_CAR(2),
    FREE(3),
    TAKEN(4),
    DELETED(0);

    private final int statusCode;

    public static Optional<DriverStatus> fromCode(int statusCode) {
        return Arrays.stream(DriverStatus.values())
                .filter(p -> p.value() == statusCode)
                .findAny();
    }

    public int value() {
        return this.statusCode;
    }
}
