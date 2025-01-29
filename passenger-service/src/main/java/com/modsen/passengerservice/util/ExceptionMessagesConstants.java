package com.modsen.passengerservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessagesConstants {

    public static final String PASSENGER_NOT_FOUND_BY_ID = "User with id %s wasn't found";
    public static final String PASSENGER_WITH_NUMBER_EXISTS = "User with phone number %s already exists";
    public static final String PASSENGER_WITH_EMAIL_EXISTS = "User with email %s already exists";

}
