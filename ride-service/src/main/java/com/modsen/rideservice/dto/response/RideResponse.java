package com.modsen.rideservice.dto.response;

import com.modsen.rideservice.model.enums.PaymentType;

public record RideResponse(
        Long id,
        String driverId,
        String passengerId,
        Double price,
        PaymentType paymentType,
        String from,
        String to
) {
}

