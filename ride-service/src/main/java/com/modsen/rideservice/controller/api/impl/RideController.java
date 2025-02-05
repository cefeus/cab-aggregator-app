package com.modsen.rideservice.controller.api.impl;

import com.modsen.rideservice.controller.api.RideApiEndpoints;
import com.modsen.rideservice.dto.request.RideRequest;
import com.modsen.rideservice.dto.response.RidePagedResponse;
import com.modsen.rideservice.dto.response.RideResponse;
import com.modsen.rideservice.service.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rides")
public class RideController implements RideApiEndpoints {

    private final RideService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RideResponse createRide(@RequestBody @Valid RideRequest dto) {
        return service.createRide(dto);
    }

    @GetMapping
    public RidePagedResponse findAll(Pageable pageable) {
        return service.findAllRides(pageable);
    }

    @GetMapping("/{id}")
    public RideResponse findById(@PathVariable Long id) {
        return service.findRideById(id);
    }

    @GetMapping("/driver/{id}")
    public RideResponse findLastByDriverId(@PathVariable String id) {
        return service.findLastRideByDriverId(id);
    }

    @GetMapping("/passenger/{id}")
    public RideResponse findLastByPassengerId(@PathVariable String id) {
        return service.findLastRideByPassengerId(id);
    }

    @PatchMapping("/{id}/in-progress")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRideStatusToInProgress(@PathVariable Long id) {
        service.updateRideStatusInProgress(id);
    }

    @PatchMapping("/{id}/finished")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRideStatusToFinished(@PathVariable Long id) {
        service.updateRideStatusFinished(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
