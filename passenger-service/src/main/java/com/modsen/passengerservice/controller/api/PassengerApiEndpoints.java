package com.modsen.passengerservice.controller.api;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Passengers controller",
        description = "Contains CRUD operations for interaction with passengers data")
public interface PassengerApiEndpoints {

    @Operation(description = "Creates new passenger with provided data")

    RegisteredPassengerResponse createPassenger(@RequestBody @Valid PassengerRegistrationRequest dto);

    @Operation(description = "Updates all provided fields for passenger with provided id")
    PassengerResponse updatePassenger(@PathVariable Long id, @RequestBody @Valid PassengerRequest dto);

    @Operation(description = "Updates phone number for passenger with provided id")
    PassengerResponse updatePassengerPhone(@PathVariable Long id, @RequestBody @Valid PhoneUpdateRequest dto);

    @Operation(description = "Retrieving all passengers via paged result that are currently active")
    PassengersPagedResponse findAllActive(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieving all passengers via paged result. Contains deleted records")
    PassengersPagedResponse findAll(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieves passenger via id if it exists")
    PassengerResponse findById(@PathVariable Long id);

    @Operation(description = "Deletes passenger via id if it exists")
    void delete(@PathVariable Long id);

    @Operation(description = "Sets passenger payment type to CASH via id if it exists")
    void setPaymentTypeCash(@PathVariable Long id);

    @Operation(description = "Sets passenger payment type to CARD via id if it exists")
    void setPaymentTypeCard(@PathVariable Long id);

}
