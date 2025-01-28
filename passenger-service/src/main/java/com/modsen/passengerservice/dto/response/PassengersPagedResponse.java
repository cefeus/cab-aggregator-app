package com.modsen.passengerservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "Data Transfer Object Page Response for Passenger ")
public record PassengersPagedResponse(
        List<PassengerResponse> passengers,
        Integer currentPage,
        Integer totalPages
) {
}
