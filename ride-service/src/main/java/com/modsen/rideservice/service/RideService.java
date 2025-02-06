package com.modsen.rideservice.service;

import com.modsen.rideservice.dto.request.RideRequest;
import com.modsen.rideservice.dto.response.RidePagedResponse;
import com.modsen.rideservice.dto.response.RideResponse;
import org.springframework.data.domain.Pageable;

public interface RideService {

    RideResponse findRideById(Long rideId);

    RideResponse findLastRideByPassengerId(String passengerId);

    RideResponse findLastRideByDriverId(String driverId);

    RidePagedResponse findAllRides(Pageable pageable);

    RideResponse createRide(RideRequest request);

    void updateRideStatusInProgress(Long id);

    void updateRideStatusFinished(Long id);

    void delete(Long id);

}
