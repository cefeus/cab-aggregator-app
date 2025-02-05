package com.modsen.driverservice.service;


import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import org.springframework.data.domain.Pageable;

public interface DriverService {

    void delete(Long id);

    DriverResponse findFirstFree();

    DriversPagedResponse getAll(Pageable pageable);

    DriversPagedResponse getAllActive(Pageable pageable);

    DriverResponse update(Long id, DriverRequest dto);

    DriverResponse findById(Long id);

    DriverResponse createDriver(DriverRequest dto);

    void addCar(Long id, String carNumber);

}
