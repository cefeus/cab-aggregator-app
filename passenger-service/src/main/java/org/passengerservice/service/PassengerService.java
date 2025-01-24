package org.passengerservice.service;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import org.springframework.data.domain.Pageable;

public interface PassengerService {

    //PassengerResponse updatePhoneNumber(String number);

    void delete(Long id);

    PassengersPagedResponse getAll(Pageable pageable);
    PassengersPagedResponse getAllActive(Pageable pageable);

    PassengerResponse update(Long id, PassengerRequest dto);

    PassengerResponse updatePhone(Long id, PhoneUpdateRequest dto);

    PassengerResponse findById(Long id);

    RegisteredPassengerResponse register(PassengerRegistrationRequest dto);

    void setPaymentTypeCash(Long id);
    void setPaymentTypeCard(Long id);
}
