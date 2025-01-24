package com.modsen.passengerservice.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PassengersPagedResponse(
        List<PassengerResponse> passengers,
        Integer currentPage,
        Integer totalPages
) {
}
