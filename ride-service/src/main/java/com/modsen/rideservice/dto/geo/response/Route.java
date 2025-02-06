package com.modsen.rideservice.dto.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record Route(

        @JsonProperty("weight_name")
        String weightName,

        @JsonProperty("weight")
        double weight,

        @JsonProperty("duration")
        double duration,

        @JsonProperty("distance")
        double distance

) {
}
