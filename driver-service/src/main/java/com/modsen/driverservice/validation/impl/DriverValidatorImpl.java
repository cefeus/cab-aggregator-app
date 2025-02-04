package com.modsen.driverservice.validation.impl;

import com.modsen.driverservice.exceptions.EmailAlreadyExistsException;
import com.modsen.driverservice.exceptions.PhoneAlreadyExistsException;
import com.modsen.driverservice.model.Driver;
import com.modsen.driverservice.model.enums.DriverStatus;
import com.modsen.driverservice.repository.DriverRepository;
import com.modsen.driverservice.validation.DriverValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_NOT_FOUND_BY_ID;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_WITH_EMAIL_EXISTS;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_WITH_NUMBER_EXISTS;

@Component
@RequiredArgsConstructor
public class DriverValidatorImpl implements DriverValidator {

    private final DriverRepository repository;

    public void checkExistsByPhone(String phone) {
        if (repository.existsByPhoneAndStatusIsNot(phone, DriverStatus.DELETED))
            throw new PhoneAlreadyExistsException(DRIVER_WITH_NUMBER_EXISTS.formatted(phone));
    }

    public void checkExistsByEmail(String email) {
        if (repository.existsByEmailAndStatusIsNot(email, DriverStatus.DELETED))
            throw new EmailAlreadyExistsException(DRIVER_WITH_EMAIL_EXISTS.formatted(email));
    }

    public Driver getOrThrow(Long id) {
        return repository.findByIdAndStatusIsNot(id, DriverStatus.DELETED)
                .orElseThrow(() -> new EntityNotFoundException(DRIVER_NOT_FOUND_BY_ID.formatted(id)));
    }

}
