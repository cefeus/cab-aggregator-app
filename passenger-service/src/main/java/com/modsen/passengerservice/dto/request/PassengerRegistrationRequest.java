package com.modsen.passengerservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.modsen.passengerservice.util.RegexpConstants.PHONE_REGEXP;

@Builder
@Schema(description = "Data Transfer Object Request for Passenger Registration")
public record PassengerRegistrationRequest(

        @Pattern(regexp = PHONE_REGEXP,
                message = "{phone.incorrect.format}")
        @Schema(description = "Passenger's phone number", example = "+375339223636")
        String phoneNumber,
        @NotBlank(message = "{firstName.not.blank}")
        @Size(min = 2, max = 100, message = "{firstName.size}")
        @Schema(description = "First name of the passenger", example = "Petr")
        String firstName,
        @NotBlank(message = "{lastName.not.blank}")
        @Size(min = 2, max = 100, message = "{lastName.size}")
        @Schema(description = "Last name of the passenger", example = "Petrov")
        String lastName
) {
}
