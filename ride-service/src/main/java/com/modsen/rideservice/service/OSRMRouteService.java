package com.modsen.rideservice.service;

import com.modsen.rideservice.dto.geo.Coordinates;
import com.modsen.rideservice.dto.geo.response.OSRMRouteResponse;

public interface OSRMRouteService {

    OSRMRouteResponse getRoute(Coordinates... coordinates);

}
