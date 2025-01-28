package com.modsen.passengerservice.dto.response;

import com.modsen.passengerservice.model.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Data Transfer Object Response for Registered Passenger")
public record RegisteredPassengerResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String rating,
        PaymentType paymentType
) {
}
