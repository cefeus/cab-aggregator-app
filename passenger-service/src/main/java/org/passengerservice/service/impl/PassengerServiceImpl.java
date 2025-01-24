package org.passengerservice.service.impl;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import com.modsen.passengerservice.exceptions.PhoneAlreadyExistsException;
import com.modsen.passengerservice.mapper.PassengerMapper;
import com.modsen.passengerservice.model.Passenger;
import com.modsen.passengerservice.model.PaymentType;
import com.modsen.passengerservice.repository.PassengerRepository;
import com.modsen.passengerservice.service.PassengerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.modsen.passengerservice.util.ExceptionMessagesConstants.*;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository repository;
    private final PassengerMapper mapper;

    @Override
    @Transactional
    public RegisteredPassengerResponse register(PassengerRegistrationRequest dto) {
        if (!repository.existsByPhoneNumberAndIsActiveIsTrue(dto.phoneNumber()))
            throw new PhoneAlreadyExistsException(String.format(PASSENGER_WITH_NUMBER_EXISTS, dto.phoneNumber()));
        var passenger = mapper.toPassenger(dto);
        repository.save(passenger);
        return mapper.toRegistered(passenger);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var passenger = getOrThrow(id);
        passenger.setIsActive(false);
    }

    @Override
    public PassengersPagedResponse getAll(Pageable pageable) {
        Page<Passenger> allPassengers = repository.findAll(pageable);
        return processPage(allPassengers);
    }

    @Override
    public PassengersPagedResponse getAllActive(Pageable pageable) {
        Page<Passenger> allActivePassengers = repository.getAllByIsActiveIsTrue(pageable);
        return processPage(allActivePassengers);
    }

    @Override
    @Transactional
    public PassengerResponse update(Long id, PassengerRequest dto) {
        var passenger = getOrThrow(id);
        if (!repository.existsByEmailAndIsActiveIsTrue(dto.email()))
            throw new PhoneAlreadyExistsException(String.format(PASSENGER_WITH_EMAIL_EXISTS, dto.email()));
        mapper.updatePassenger(dto, passenger);
        return mapper.toResponse(passenger);
    }

    @Override
    public PassengerResponse updatePhone(Long id, PhoneUpdateRequest dto) {
        var passenger = getOrThrow(id);
        if (!repository.existsByPhoneNumberAndIsActiveIsTrue(dto.phone()))
            throw new PhoneAlreadyExistsException(String.format(PASSENGER_WITH_NUMBER_EXISTS, dto.phone()));
        passenger.setPhoneNumber(dto.phone());
        return null;
    }

    @Override
    public PassengerResponse findById(Long id) {
        var passenger = getOrThrow(id);
        return mapper.toResponse(passenger);
    }

    public void setPaymentTypeCard(Long id) {
        var passenger = getOrThrow(id);
        repository.updatePaymentType(PaymentType.CARD.name(), id);
    }

    public void setPaymentTypeCash(Long id) {
        var passenger = getOrThrow(id);
        repository.updatePaymentType(PaymentType.CASH.name(), id);
    }

    private Passenger getOrThrow(Long id) {
        return repository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PASSENGER_NOT_FOUND_BY_ID, id)));
    }

    private PassengersPagedResponse processPage(Page<Passenger> passengers) {
        return PassengersPagedResponse.builder()
                .passengers(mapper.toList(passengers.getContent()))
                .currentPage(passengers.getNumber())
                .totalPages(passengers.getTotalPages())
                .build();
    }
}
