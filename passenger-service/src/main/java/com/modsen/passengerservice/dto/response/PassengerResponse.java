package com.modsen.passengerservice.dto.response;

import com.modsen.passengerservice.model.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Data Transfer Object Response for Passenger")
public record PassengerResponse(
        Long id,
        String uuid,
        String phoneNumber,
        String firstName,
        String lastName,
        String rating,
        PaymentType paymentType,
        String email
) {
}
