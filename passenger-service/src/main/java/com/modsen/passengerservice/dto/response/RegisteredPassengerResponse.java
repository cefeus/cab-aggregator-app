package com.modsen.passengerservice.dto.response;

import com.modsen.passengerservice.model.PaymentType;
import lombok.Builder;

@Builder
public record RegisteredPassengerResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String rating,
        PaymentType paymentType
) {
}
