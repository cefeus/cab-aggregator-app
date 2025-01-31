package com.modsen.driverservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "Data Transfer Object Response for Driver")
public record DriverResponse(

        Long id,

        String phone,

        String email,

        String firstName,

        String lastName,

        String sex,

        String rating,

        List<Long> carIds
) {
}
