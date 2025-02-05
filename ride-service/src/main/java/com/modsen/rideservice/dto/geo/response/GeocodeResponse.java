package com.modsen.rideservice.dto.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeocodeResponse(

        @JsonProperty("place_id")
         String placeId,

         @JsonProperty("display_name")
         String displayName,

         @JsonProperty("lat")
         double lat,

         @JsonProperty("lon")
         double lon,

        @JsonProperty("boundingbox")
         List<String> boundingBox
) {
}
