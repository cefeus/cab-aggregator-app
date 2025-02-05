package com.modsen.rideservice.service;

import com.modsen.rideservice.dto.geo.response.GeocodeResponse;

public interface GeocodeService {

    GeocodeResponse getGeocode(String address);
}
