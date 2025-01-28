package com.modsen.passengerservice.controller.api.impl;

import com.modsen.passengerservice.controller.api.PassengerApiEndpoints;
import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import com.modsen.passengerservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengerController implements PassengerApiEndpoints {

    private final PassengerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredPassengerResponse createPassenger(@RequestBody PassengerRegistrationRequest dto) {
        return service.createPassenger(dto);
    }

    @PutMapping("/{id}")
    public PassengerResponse updatePassenger(@PathVariable Long id, @RequestBody PassengerRequest dto) {
        return service.update(id, dto);
    }

    @PutMapping("/phone/{id}")
    public PassengerResponse updatePassengerPhone(@PathVariable Long id, @RequestBody PhoneUpdateRequest dto) {
        return service.updatePhone(id, dto);
    }

    @GetMapping
    public PassengersPagedResponse findAllActive(Pageable pageable) {
        return service.getAllActive(pageable);
    }

    @GetMapping("/admin/all")
    public PassengersPagedResponse findAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public  PassengerResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/cash/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setPaymentTypeCash(@PathVariable Long id) {
        service.setPaymentTypeCash(id);
    }

    @PatchMapping("/card/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setPaymentTypeCard(@PathVariable Long id) {
        service.setPaymentTypeCard(id);
    }

}
