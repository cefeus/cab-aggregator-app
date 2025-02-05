package com.modsen.rideservice.service.impl;

import com.modsen.rideservice.dto.request.RideRequest;
import com.modsen.rideservice.dto.response.RidePagedResponse;
import com.modsen.rideservice.dto.response.RideResponse;
import com.modsen.rideservice.exceptions.RideStatusException;
import com.modsen.rideservice.mapper.RideMapper;
import com.modsen.rideservice.model.Ride;
import com.modsen.rideservice.model.enums.RideStatus;
import com.modsen.rideservice.repository.RideRepository;
import com.modsen.rideservice.service.PriceService;
import com.modsen.rideservice.service.RideService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.modsen.rideservice.util.ExceptionMessagesConstants.RIDE_NOT_FOUND_BY_DRIVER_ID;
import static com.modsen.rideservice.util.ExceptionMessagesConstants.RIDE_NOT_FOUND_BY_ID;
import static com.modsen.rideservice.util.ExceptionMessagesConstants.RIDE_NOT_FOUND_BY_PASSENGER_ID;
import static com.modsen.rideservice.util.ExceptionMessagesConstants.RIDE_STATUS_NOT_IN_PROGRESS;
import static com.modsen.rideservice.util.ExceptionMessagesConstants.RIDE_STATUS_NOT_STARTED;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository repository;
    private final RideMapper mapper;
    private final PriceService priceService;

    @Override
    @Transactional
    public RideResponse createRide(RideRequest request) {
        var ride = mapper.toEntity(request);

        double price = priceService.calculatePrice(request.from(), request.to());
        ride.setPrice(price);
        ride.setRideStatus(RideStatus.STARTED);
        repository.save(ride);
        return mapper.toResponse(ride);
    }

    @Override
    public RideResponse findRideById(Long rideId) {
        var ride = getOrThrow(rideId);
        return mapper.toResponse(ride);
    }

    @Override
    public RideResponse findLastRideByPassengerId(String passengerId) {
        var ride = repository.findFirstByPassengerIdOrderByCreatedAt(passengerId).orElseThrow(
                () -> new EntityNotFoundException(RIDE_NOT_FOUND_BY_PASSENGER_ID.formatted(passengerId)));
        return mapper.toResponse(ride);
    }

    @Override
    public RideResponse findLastRideByDriverId(String driverId) {
        var ride = repository.findFirstByDriverIdOrderByCreatedAt(driverId).orElseThrow(
                () -> new EntityNotFoundException(RIDE_NOT_FOUND_BY_DRIVER_ID.formatted(driverId)));
        return mapper.toResponse(ride);
    }

    @Override
    public RidePagedResponse findAllRides(Pageable pageable) {
        Page<Ride> rides = repository.findAll(pageable);
        return mapper.mapRidesToPagedResponse(pageable.getPageNumber(), rides);
    }

    @Transactional
    @Override
    public void updateRideStatusFinished(Long id) {
        var ride = getOrThrow(id);
        var status = ride.getRideStatus();

        if (!status.equals(RideStatus.IN_PROGRESS))
            throw new RideStatusException(RIDE_STATUS_NOT_IN_PROGRESS.formatted(status));

        ride.setRideStatus(RideStatus.FINISHED);
    }

    @Transactional
    @Override
    public void updateRideStatusInProgress(Long id) {
        var ride = getOrThrow(id);
        var status = ride.getRideStatus();

        if (!status.equals(RideStatus.STARTED))
            throw new RideStatusException(RIDE_STATUS_NOT_STARTED.formatted(status));

        ride.setRideStatus(RideStatus.IN_PROGRESS);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var ride = getOrThrow(id);
        ride.setRideStatus(RideStatus.DELETED);
    }

    public Ride getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(RIDE_NOT_FOUND_BY_ID.formatted(id)));
    }

}
