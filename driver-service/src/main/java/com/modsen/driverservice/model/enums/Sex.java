package com.modsen.driverservice.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public enum Sex {
    MALE(1),
    FEMALE(2),
    OTHER(0);

    private final int code;

    public static Optional<Sex> fromCode(int code) {
        return Arrays.stream(Sex.values())
                .filter(s -> s.value() == code)
                .findAny();
    }

    public int value() {
        return this.code;
    }

}
