package com.modsen.rideservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

public record RideRequest(

        @NotBlank(message = "{driver.id.not.blank}")
        @UUID(message = "{driver.id.uuid.format}")
        String driverId,

        @NotBlank(message = "{passenger.id.not.blank}")
        @UUID(message = "{passenger.id.uuid.format}")
        String passengerId,

        @NotNull(message = "{from.not.null}")
        @NotBlank(message = "{from.not.blank}")
        String from,

        @NotNull(message = "{to.not.null}")
        @NotBlank(message = "{to.not.blank}")
        String to,

        @NotNull(message = "{payment.type.not.null}")
        @NotBlank(message = "{payment.type.not.blank}")
        String paymentType
) {
}
