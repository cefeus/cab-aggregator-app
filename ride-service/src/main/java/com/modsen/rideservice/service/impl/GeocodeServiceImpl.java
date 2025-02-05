package com.modsen.rideservice.service.impl;

import com.modsen.rideservice.dto.geo.response.GeocodeResponse;
import com.modsen.rideservice.service.GeocodeService;
import com.modsen.rideservice.util.RoutingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GeocodeServiceImpl implements GeocodeService {

    private final RestTemplate restTemplate;

    @Value("${route.api-key}")
    private String apiKey;

    @Override
    public GeocodeResponse getGeocode(String address) {
        URI uri = UriComponentsBuilder.fromUriString(RoutingConstants.GEOCODE_API_URL)
                .queryParam("q", address)
                .queryParam("api_key", apiKey)
                .build()
                .toUri();

        GeocodeResponse[] response = restTemplate.getForObject(uri, GeocodeResponse[].class);
        return Objects.requireNonNull(response[0]);
    }

}
