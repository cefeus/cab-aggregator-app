package com.modsen.driverservice.validation.impl;

import com.modsen.driverservice.exceptions.NumberAlreadyExistsException;
import com.modsen.driverservice.model.Car;
import com.modsen.driverservice.repository.CarRepository;
import com.modsen.driverservice.validation.CarValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_ID;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_NUMBER;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_WITH_NUMBER_EXISTS;

@Component
@RequiredArgsConstructor
public class CarValidatorImpl implements CarValidator {

    private final CarRepository repository;

    public void checkExistsByNumber(String number) {
        if (repository.existsByCarNumberAndIsActiveIsTrue(number))
            throw new NumberAlreadyExistsException(CAR_WITH_NUMBER_EXISTS.formatted(number));
    }

    public Car getOrThrow(Long id) {
        return repository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_ID.formatted(id)));
    }

    public Car getOrThrow(String carNumber) {
        return repository.findByCarNumberAndIsActiveIsTrue(carNumber)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_NUMBER.formatted(carNumber)));
    }
}
