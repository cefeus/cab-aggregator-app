package com.modsen.driverservice.service.impl;

import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import com.modsen.driverservice.exceptions.NoDriverAvailableException;
import com.modsen.driverservice.mapper.DriverMapper;
import com.modsen.driverservice.model.Driver;
import com.modsen.driverservice.model.enums.DriverStatus;
import com.modsen.driverservice.repository.CarRepository;
import com.modsen.driverservice.repository.DriverRepository;
import com.modsen.driverservice.service.DriverService;
import com.modsen.driverservice.validation.DriverValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_NUMBER;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.NO_DRIVER_AVAILABLE;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;
    private final CarRepository carRepository;
    private final DriverMapper mapper;
    private final DriverValidator validator;

    @Override
    @Transactional
    public DriverResponse createDriver(DriverRequest dto) {
        validator.checkExistsByPhone(dto.phone());
        validator.checkExistsByEmail(dto.email());
        var driver = mapper.toDriver(dto);
        driver.setStatus(DriverStatus.CREATED);
        repository.save(driver);
        return mapper.toRegistered(driver);
    }

    @Override
    @Transactional
    public void addCar(Long id, String carNumber) {
        var driver = validator.getOrThrow(id);
        var car = carRepository.findByCarNumberAndIsActiveIsTrue(carNumber)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_NUMBER.formatted(carNumber)));

        driver.getCars().add(car);
        car.getDrivers().add(driver);

        driver.setStatus(DriverStatus.FREE);
    }

    @Override
    @Transactional
    public DriverResponse findFirstFree() {
        var driver = repository.findFirstByStatus(DriverStatus.FREE)
                .orElseThrow(() -> new NoDriverAvailableException(NO_DRIVER_AVAILABLE));
        driver.setStatus(DriverStatus.TAKEN);
        var response = mapper.toResponse(driver);
        return response;
    }

    @Override
    public DriversPagedResponse getAll(Pageable pageable) {
        Page<Driver> allDrivers = repository.findAll(pageable);
        var response = mapper.mapDriversToPagedResponse(pageable.getPageNumber(), allDrivers);
        return response;
    }

    @Override
    public DriversPagedResponse getAllActive(Pageable pageable) {
        Page<Driver> allActiveDrivers = repository.findAllByStatusIsNot(pageable, DriverStatus.DELETED);
        var response = mapper.mapDriversToPagedResponse(pageable.getPageNumber(), allActiveDrivers);
        return response;
    }

    @Override
    public DriverResponse findById(Long id) {
        var driver = validator.getOrThrow(id);
        return mapper.toResponse(driver);
    }

    @Override
    @Transactional
    public DriverResponse update(Long id, DriverRequest dto) {
        var driver = validator.getOrThrow(id);
        validator.checkExistsByPhone(dto.phone());
        validator.checkExistsByEmail(dto.email());
        mapper.updateDriver(dto, driver);
        return mapper.toResponse(driver);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var driver = validator.getOrThrow(id);
        driver.getCars().clear();
        driver.setStatus(DriverStatus.DELETED);
    }

}
