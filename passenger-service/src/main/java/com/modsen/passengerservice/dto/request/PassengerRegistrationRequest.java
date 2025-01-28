package com.modsen.passengerservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import static com.modsen.passengerservice.util.RegexpConstants.*;

@Builder
public record PassengerRegistrationRequest(
        @Pattern(regexp = PHONE_REGEXP,
                message = "{phone.incorrect.format}")
        String phoneNumber,
        @NotBlank(message = "{firstName.not.blank}")
        @Size(min = 2, max = 100, message = "{firstName.size}")
        String firstName,
        @NotBlank(message = "{lastName.not.blank}")
        @Size(min = 2, max = 100, message = "{lastName.size}")
        String lastName
) {
}
