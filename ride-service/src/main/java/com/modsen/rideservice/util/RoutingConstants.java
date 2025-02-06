package com.modsen.rideservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoutingConstants {

    public static final String GEOCODE_API_URL = "https://geocode.maps.co/search";

    public static final String OSRM_API_URL = "http://router.project-osrm.org";

    public static final String OSRM_DRIVER_PATH = "/route/v1/driving/";

}
