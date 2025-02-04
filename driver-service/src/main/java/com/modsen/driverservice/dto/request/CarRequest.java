package com.modsen.driverservice.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Data Transfer Object Request for cars")
public record CarRequest(
        @NotBlank(message = "{carNumber.not.blank}")
        @Size(min = 2, max = 100, message = "{carNumber.size}")
        @Schema(description = "Car number", example = "2484EC-7")
        String carNumber,

        @NotBlank(message = "{brand.not.blank}")
        @Size(min = 2, max = 100, message = "{brand.size}")
        @Schema(description = "Car brand", example = "Volkswagen")
        String brand,

        @NotBlank(message = "{color.not.blank}")
        @Size(min = 2, max = 100, message = "{color.size}")
        @Schema(description = "Car color", example = "Black")
        String color
) {
}
