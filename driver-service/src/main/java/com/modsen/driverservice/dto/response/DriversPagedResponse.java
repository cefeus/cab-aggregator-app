package com.modsen.driverservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "Data Transfer Object Page Response for Drivers")
public record DriversPagedResponse(
        List<DriverResponse> drivers,
        Integer currentPage,
        Integer totalPages
) {
}
