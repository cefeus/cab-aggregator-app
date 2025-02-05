package com.modsen.rideservice.service.impl;

import com.modsen.rideservice.dto.geo.Coordinates;
import com.modsen.rideservice.dto.geo.response.OSRMRouteResponse;
import com.modsen.rideservice.exceptions.RouteArgumentsException;
import com.modsen.rideservice.service.OSRMRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.modsen.rideservice.util.ExceptionMessagesConstants.ROUTE_ARGUMENTS;
import static com.modsen.rideservice.util.RoutingConstants.OSRM_API_URL;
import static com.modsen.rideservice.util.RoutingConstants.OSRM_DRIVER_PATH;

@Service
@RequiredArgsConstructor
public class OSRMRouteServiceImpl implements OSRMRouteService {

    private final RestTemplate restTemplate;

    public OSRMRouteResponse getRoute(Coordinates... coordinates) {
        if (coordinates == null || coordinates.length < 2) {
            throw new RouteArgumentsException(ROUTE_ARGUMENTS);
        }

        String coordinateString = Stream.of(coordinates)
                .map(coord -> coord.longitude() + "," + coord.latitude())
                .collect(Collectors.joining(";"));

        URI uri = UriComponentsBuilder.fromUriString(OSRM_API_URL)
                .path(OSRM_DRIVER_PATH + coordinateString)
                .queryParam("overview", "false")
                .build()
                .toUri();

        OSRMRouteResponse osrmRoute = restTemplate.getForObject(uri, OSRMRouteResponse.class);
        return osrmRoute;
    }

}
