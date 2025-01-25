package com.modsen.passengerservice.controller.api.impl;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PassengerApiEndpoints {

    RegisteredPassengerResponse createPassenger(@RequestBody @Valid PassengerRegistrationRequest dto);

    PassengerResponse updatePassenger(@PathVariable Long id, @RequestBody @Valid PassengerRequest dto);

    PassengerResponse updatePassengerPhone(@PathVariable Long id, @RequestBody @Valid PhoneUpdateRequest dto);

    PassengersPagedResponse findAllActive(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    PassengersPagedResponse findAll(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    PassengerResponse findById(@PathVariable Long id);

    void delete(@PathVariable Long id);

    void setPaymentType(@PathVariable Long id);

    void setPaymentTypeCard(@PathVariable Long id);

}
