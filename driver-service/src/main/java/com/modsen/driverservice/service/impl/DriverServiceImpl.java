package com.modsen.driverservice.service.impl;

import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import com.modsen.driverservice.exceptions.EmailAlreadyExistsException;
import com.modsen.driverservice.exceptions.NoDriverAvailableException;
import com.modsen.driverservice.exceptions.PhoneAlreadyExistsException;
import com.modsen.driverservice.mapper.DriverMapper;
import com.modsen.driverservice.model.Driver;
import com.modsen.driverservice.model.enums.DriverStatus;
import com.modsen.driverservice.repository.CarRepository;
import com.modsen.driverservice.repository.DriverRepository;
import com.modsen.driverservice.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_NUMBER;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_NOT_FOUND_BY_ID;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_WITH_EMAIL_EXISTS;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.DRIVER_WITH_NUMBER_EXISTS;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.NO_DRIVER_AVAILABLE;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;
    private final CarRepository carRepository;
    private final DriverMapper mapper;

    @Override
    @Transactional
    public DriverResponse createDriver(DriverRequest dto) {
        checkExistsByPhone(dto.phone());
        checkExistsByEmail(dto.email());
        var driver = mapper.toDriver(dto);
        driver.setStatus(DriverStatus.CREATED);
        repository.save(driver);
        return mapper.toRegistered(driver);
    }

    @Override
    @Transactional
    public void addCar(Long id, String carNumber) {
        var driver = getOrThrow(id);
        var car = carRepository.findByCarNumberAndIsActiveIsTrue(carNumber)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_NUMBER.formatted(carNumber)));

        driver.getCars().add(car);
        car.getDrivers().add(driver);

        driver.setStatus(DriverStatus.FREE);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var driver = getOrThrow(id);
        driver.getCars().clear();
        driver.setStatus(DriverStatus.DELETED);
    }

    @Override
    @Transactional
    public DriverResponse findFirstFree() {
        var driver = repository.findFirstByStatus(DriverStatus.FREE)
                .orElseThrow(() -> new NoDriverAvailableException(NO_DRIVER_AVAILABLE));
        driver.setStatus(DriverStatus.TAKEN);
        return mapper.toResponse(driver);
    }

    @Override
    public DriversPagedResponse getAll(Pageable pageable) {
        Page<Driver> allDrivers = repository.findAll(pageable);
        return processPage(allDrivers);
    }

    @Override
    public DriversPagedResponse getAllActive(Pageable pageable) {
        Page<Driver> allActiveDrivers = repository.findAllByStatusIsNot(pageable, DriverStatus.DELETED);
        return processPage(allActiveDrivers);
    }

    @Override
    @Transactional
    public DriverResponse update(Long id, DriverRequest dto) {
        var driver = getOrThrow(id);
        checkExistsByPhone(dto.phone());
        checkExistsByEmail(dto.email());
        mapper.updateDriver(dto, driver);
        return mapper.toResponse(driver);
    }

    @Override
    public DriverResponse findById(Long id) {
        var driver = getOrThrow(id);
        return mapper.toResponse(driver);
    }

    private void checkExistsByPhone(String phone) {
        if (repository.existsByPhoneAndStatusIsNot(phone, DriverStatus.DELETED))
            throw new PhoneAlreadyExistsException(DRIVER_WITH_NUMBER_EXISTS.formatted(phone));
    }

    private void checkExistsByEmail(String email) {
        if (repository.existsByEmailAndStatusIsNot(email, DriverStatus.DELETED))
            throw new EmailAlreadyExistsException(DRIVER_WITH_EMAIL_EXISTS.formatted(email));
    }

    private Driver getOrThrow(Long id) {
        return repository.findByIdAndStatusIsNot(id, DriverStatus.DELETED)
                .orElseThrow(() -> new EntityNotFoundException(DRIVER_NOT_FOUND_BY_ID.formatted(id)));
    }

    private DriversPagedResponse processPage(Page<Driver> drivers) {
        return DriversPagedResponse.builder()
                .drivers(mapper.toList(drivers.getContent()))
                .currentPage(drivers.getNumber())
                .totalPages(drivers.getTotalPages())
                .build();
    }
}
