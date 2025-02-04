package com.modsen.driverservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessagesConstants {

    public static final String DRIVER_NOT_FOUND_BY_ID = "Driver with id %s wasn't found ";
    public static final String CAR_NOT_FOUND_BY_ID = "Car with id %s wasn't found ";
    public static final String CAR_NOT_FOUND_BY_NUMBER = "Car with number %s wasn't found ";
    public static final String DRIVER_WITH_NUMBER_EXISTS = "Driver with phone number %s already exists ";
    public static final String CAR_WITH_NUMBER_EXISTS = "Car with number %s already exists ";
    public static final String DRIVER_WITH_EMAIL_EXISTS = "Driver with email %s already exists ";
    public static final String NO_DRIVER_AVAILABLE = "There were no available drivers found ";

    public static final String UNKNOWN_ARGUMENT = "Unknown attribute %s";

}
