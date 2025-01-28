package com.modsen.passengerservice.mapper;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import com.modsen.passengerservice.model.Passenger;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PassengerMapper {


    Passenger toPassenger(PassengerRegistrationRequest passengerRequest);

    Passenger updatePassenger(PassengerRequest dto, @MappingTarget Passenger passenger);

    @Mapping(expression = "java(PaymentType.castIntToPaymentType(passenger.getPaymentType()).get())", target = "paymentType")
    PassengerResponse toResponse(Passenger passenger);

    List<PassengerResponse> toList(List<Passenger> passengers);

    @Mapping(target = "rating", constant = "5.0")
    @Mapping(target = "paymentType", constant = "CASH")
    RegisteredPassengerResponse toRegistered(Passenger passenger);

}
