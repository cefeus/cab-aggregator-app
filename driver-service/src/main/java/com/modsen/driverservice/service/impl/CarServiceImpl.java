package com.modsen.driverservice.service.impl;

import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
import com.modsen.driverservice.mapper.CarMapper;
import com.modsen.driverservice.model.Car;
import com.modsen.driverservice.repository.CarRepository;
import com.modsen.driverservice.service.CarService;
import com.modsen.driverservice.validation.CarValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarMapper mapper;
    private final CarValidator validator;

    @Override
    @Transactional
    public CarResponse createCar(CarRequest dto) {
        validator.checkExistsByNumber(dto.carNumber());
        var car = mapper.toCar(dto);
        repository.save(car);
        return mapper.toRegistered(car);
    }

    @Override
    public CarsPagedResponse getAll(Pageable pageable) {
        Page<Car> allCars = repository.findAll(pageable);
        return mapper.mapCarsToPagedResponse(
                pageable.getPageNumber(),
                allCars);
    }

    @Override
    public CarsPagedResponse getAllActive(Pageable pageable) {
        Page<Car> allActiveCars = repository.findAllByIsActiveIsTrue(pageable);
        return mapper.mapCarsToPagedResponse(
                pageable.getPageNumber(),
                allActiveCars);
    }

    @Override
    public CarResponse findById(Long id) {
        var driver = validator.getOrThrow(id);
        return mapper.toResponse(driver);
    }

    @Override
    public CarResponse findByCarNumber(String carNumber) {
        var car = validator.getOrThrow(carNumber);
        return mapper.toResponse(car);
    }

    @Override
    @Transactional
    public CarResponse update(Long id, CarRequest dto) {
        var car = validator.getOrThrow(id);
        validator.checkExistsByNumber(dto.carNumber());
        mapper.updateCar(dto, car);
        return mapper.toResponse(car);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var car = validator.getOrThrow(id);
        car.setActive(false);
    }

}
