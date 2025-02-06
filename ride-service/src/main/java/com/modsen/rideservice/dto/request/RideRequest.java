package com.modsen.rideservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public record RideRequest(

        @NotBlank(message = "{driver_id.not.blank}")
        @UUID(message = "{driver_id.uuid.format}")
        String driverId,

        @NotBlank(message = "{passenger_id.not.blank}")
        @UUID(message = "{passenger_id.uuid.format}")
        String passengerId,

        @NotBlank(message = "{from.not.blank}")
        String from,

        @NotBlank(message = "{to.not.blank}")
        String to,

        @NotBlank(message = "{payment_type.not.blank}")
        String paymentType

) {
}
