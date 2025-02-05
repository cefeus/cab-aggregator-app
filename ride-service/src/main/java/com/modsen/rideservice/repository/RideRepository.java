package com.modsen.rideservice.repository;

import com.modsen.rideservice.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    Optional<Ride> findFirstByDriverIdOrderByCreatedAt(String driverId);

    Optional<Ride> findFirstByPassengerIdOrderByCreatedAt(String passengerId);

}
