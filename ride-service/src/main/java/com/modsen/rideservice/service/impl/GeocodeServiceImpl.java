package com.modsen.rideservice.service.impl;

import com.modsen.rideservice.dto.geo.response.GeocodeResponse;
import com.modsen.rideservice.service.GeocodeService;
import com.modsen.rideservice.util.RoutingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class GeocodeServiceImpl implements GeocodeService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public GeocodeResponse getGeocode(String address) {
        URI uri = UriComponentsBuilder.fromUriString(RoutingConstants.GEOCODE_API_URL)
                .queryParam("q", address)
                .queryParam("api_key", RoutingConstants.GEOCODE_API_KEY)
                .build()
                .toUri();

        GeocodeResponse[] response = restTemplate.getForObject(uri, GeocodeResponse[].class);
        return (response != null && response.length > 0) ? response[0] : null;
    }
}
