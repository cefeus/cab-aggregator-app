package org.passengerservice.dto.request;

import jakarta.validation.constraints.Pattern;

import static com.modsen.passengerservice.util.RegexpConstants.PHONE_REGEXP;

public record PhoneUpdateRequest(
        @Pattern(regexp = PHONE_REGEXP,
                message = "phone.incorrect.format")
        String phone
) {
}
