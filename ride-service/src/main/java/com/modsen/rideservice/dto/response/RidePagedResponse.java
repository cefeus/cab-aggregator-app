package com.modsen.rideservice.dto.response;

import java.util.List;

public record RidePagedResponse(

        List<RideResponse> rides,

        Integer currentPage,

        Integer totalPages

) {
}
