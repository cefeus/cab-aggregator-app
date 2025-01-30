package com.modsen.driverservice.controller.api.impl;


import com.modsen.driverservice.controller.api.DriverApiEndpoints;
import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import com.modsen.driverservice.service.DriverService;
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
@RequestMapping("/api/v1/drivers")
public class DriverController implements DriverApiEndpoints {

    private final DriverService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponse createDriver(@RequestBody DriverRequest dto) {
        return service.createDriver(dto);
    }

    @PutMapping("/{id}")
    public DriverResponse updateDriver(@PathVariable Long id, @RequestBody DriverRequest dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public DriversPagedResponse findAllActive(Pageable pageable) {
        return service.getAllActive(pageable);
    }

    @GetMapping("/admin/all")
    public DriversPagedResponse findAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public DriverResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Override
    @GetMapping
    public DriverResponse getAnyFreeDriver() {
        return service.findFirstFree();
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCar(@PathVariable Long id, @PathVariable String carNumber) {
        service.addCar(id, carNumber);
    }

}
