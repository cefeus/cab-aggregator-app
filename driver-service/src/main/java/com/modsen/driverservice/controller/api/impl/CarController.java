package com.modsen.driverservice.controller.api.impl;


import com.modsen.driverservice.controller.api.CarApiEndpoints;
import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
import com.modsen.driverservice.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController implements CarApiEndpoints {

    private final CarService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse createCar(@RequestBody @Valid CarRequest dto) {
        return service.createCar(dto);
    }

    @PutMapping("/{id}")
    public CarResponse updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public CarsPagedResponse findAllActive(Pageable pageable) {
        return service.getAllActive(pageable);
    }

    @GetMapping("/admin/all")
    public CarsPagedResponse findAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{number}")
    public CarResponse findByCarNumber(@PathVariable String number) {
        return service.findByCarNumber(number);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
