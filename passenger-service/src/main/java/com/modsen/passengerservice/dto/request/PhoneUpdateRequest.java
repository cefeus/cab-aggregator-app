package com.modsen.passengerservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import static com.modsen.passengerservice.util.RegexpConstants.*;

@Schema(description = "Data Transfer Object Request for Passenger Phone Update")
public record PhoneUpdateRequest(
        @Pattern(regexp = PHONE_REGEXP,
                message = "{phone.incorrect.format}")
        @Schema(description = "Passenger's phone number", example = "+375339223636")
        String phoneNumber
) {
}
