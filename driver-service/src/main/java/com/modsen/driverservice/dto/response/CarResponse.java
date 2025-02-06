package com.modsen.driverservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder

@Schema(description = "Data Transfer Object Response for Car")
public record CarResponse(

        Long id,

        String uuid,

        String carNumber,

        String brand,

        String color,

        List<Long> driverIds
) {
}
