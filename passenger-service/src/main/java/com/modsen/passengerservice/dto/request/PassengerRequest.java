package com.modsen.passengerservice.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Data Transfer Object Request for Passenger")
public record PassengerRequest(
        @NotBlank(message = "{firstName.not.blank}")
        @Size(min = 2, max = 100, message = "{firstName.size}")
        @Schema(description = "First name of the passenger", example = "Petr")
        String firstName,
        @NotBlank(message = "{lastName.not.blank}")
        @Size(min = 2, max = 100, message = "{lastName.size}")
        @Schema(description = "Last name of the passenger", example = "Petrov")
        String lastName,
        @Nullable
        @Email(message = "{email.valid}")
        @Schema(description = "Passenger's email", example = "petrov@gmail.com")
        String email
) {
}
