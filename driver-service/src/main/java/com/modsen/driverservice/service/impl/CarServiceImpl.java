package com.modsen.driverservice.service.impl;

import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
import com.modsen.driverservice.exceptions.NumberAlreadyExistsException;
import com.modsen.driverservice.mapper.CarMapper;
import com.modsen.driverservice.model.Car;
import com.modsen.driverservice.repository.CarRepository;
import com.modsen.driverservice.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_ID;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_NOT_FOUND_BY_NUMBER;
import static com.modsen.driverservice.util.ExceptionMessagesConstants.CAR_WITH_NUMBER_EXISTS;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    @Override
    @Transactional
    public CarResponse createCar(CarRequest dto) {
        checkExistsByNumber(dto.carNumber());
        var car = mapper.toCar(dto);
        repository.save(car);
        return mapper.toRegistered(car);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var car = getOrThrow(id);
        car.setActive(false);
    }

    @Override
    public CarsPagedResponse getAll(Pageable pageable) {
        Page<Car> allCars = repository.findAll(pageable);
        return processPage(allCars);
    }

    @Override
    public CarsPagedResponse getAllActive(Pageable pageable) {
        Page<Car> allActiveCars = repository.findAllByIsActiveIsTrue(pageable);
        return processPage(allActiveCars);
    }

    @Override
    @Transactional
    public CarResponse update(Long id, CarRequest dto) {
        var car = getOrThrow(id);
        checkExistsByNumber(dto.carNumber());
        mapper.updateCar(dto, car);
        return mapper.toResponse(car);
    }

    @Override
    public CarResponse findById(Long id) {
        var driver = getOrThrow(id);
        return mapper.toResponse(driver);
    }

    @Override
    public CarResponse findByCarNumber(String carNumber) {
        var car = getOrThrow(carNumber);
        return mapper.toResponse(car);
    }

    private void checkExistsByNumber(String number) {
        if (repository.existsByCarNumberAndIsActiveIsTrue(number))
            throw new NumberAlreadyExistsException(CAR_WITH_NUMBER_EXISTS.formatted(number));
    }

    private Car getOrThrow(Long id) {
        return repository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_ID.formatted(id)));
    }

    private Car getOrThrow(String carNumber) {
        return repository.findByCarNumberAndIsActiveIsTrue(carNumber)
                .orElseThrow(() -> new EntityNotFoundException(CAR_NOT_FOUND_BY_NUMBER.formatted(carNumber)));
    }

    private CarsPagedResponse processPage(Page<Car> cars) {
        return CarsPagedResponse.builder()
                .cars(mapper.toList(cars.getContent()))
                .currentPage(cars.getNumber())
                .totalPages(cars.getTotalPages())
                .build();
    }
}
