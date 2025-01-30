package com.modsen.driverservice.service;


import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
import org.springframework.data.domain.Pageable;

public interface CarService {

    void delete(Long id);

    CarsPagedResponse getAll(Pageable pageable);

    CarsPagedResponse getAllActive(Pageable pageable);

    CarResponse update(Long id, CarRequest dto);

    CarResponse findById(Long id);

    CarResponse createCar(CarRequest dto);

    CarResponse findByCarNumber(String carNumber);
}
