package org.passengerservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessagesConstants {

    public final String PASSENGER_NOT_FOUND_BY_ID = "User with id %s wasn't found";
    public final String PASSENGER_WITH_NUMBER_EXISTS = "User with phone number %s already exists";
    public final String PASSENGER_WITH_EMAIL_EXISTS = "User with email %s already exists";

}
