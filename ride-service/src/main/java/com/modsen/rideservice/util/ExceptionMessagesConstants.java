package com.modsen.rideservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionMessagesConstants {

    public static final String UNKNOWN_ARGUMENT = "Unknown attribute %s";

    public static final String RIDE_NOT_FOUND_BY_ID = "Ride with id %s wasn't found";

    public static final String RIDE_NOT_FOUND_BY_PASSENGER_ID = "Ride with passenger id %s wasn't found";
    public static final String RIDE_NOT_FOUND_BY_DRIVER_ID = "Ride with driver id %s wasn't found";

    public static final String RIDE_STATUS_NOT_IN_PROGRESS = "Can't set ride status to IN_PROGRESS. Current ride status isn't STARTED, but %s";

    public static final String RIDE_STATUS_NOT_STARTED = "Can't set ride status to FINISHED. Current ride status isn't IN_PROGRESS, but %s";

    public static final String ROUTE_ARGUMENTS = "At least two coordinate pairs are required";

}
