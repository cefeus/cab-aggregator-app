package com.modsen.passengerservice.mapper;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import com.modsen.passengerservice.model.Passenger;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PassengerMapper {

    Passenger toPassenger(PassengerRegistrationRequest passengerRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Passenger updatePassenger(PassengerRequest dto, @MappingTarget Passenger passenger);

    PassengerResponse toResponse(Passenger passenger);

    List<PassengerResponse> toList(List<Passenger> passengers);

    @Mapping(target = "rating", constant = "5.0")
    @Mapping(target = "paymentType", constant = "CASH")
    RegisteredPassengerResponse toRegistered(Passenger passenger);

}
