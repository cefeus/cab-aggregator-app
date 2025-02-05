package com.modsen.rideservice.dto.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OSRMRouteResponse(

        @JsonProperty("code")
        String code,

        @JsonProperty("routes")
        List<Route> routes

) {
}
