package com.modsen.passengerservice.dto.response;

import com.modsen.passengerservice.model.PaymentType;
import lombok.Builder;

@Builder
public record PassengerResponse(
        Long id,
        String phoneNumber,
        String firstName,
        String lastName,
        String rating,
        PaymentType paymentType,
        String email
) {
}
