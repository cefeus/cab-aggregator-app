package com.modsen.passengerservice.repository;


import com.modsen.passengerservice.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    boolean existsByPhoneNumberAndIsActiveIsTrue(String number);

    Page<Passenger> getAllByIsActiveIsTrue(Pageable pageable);

    Optional<Passenger> findByIdAndIsActiveIsTrue(Long id);

    boolean existsByEmailAndIsActiveIsTrue(String email);

}
