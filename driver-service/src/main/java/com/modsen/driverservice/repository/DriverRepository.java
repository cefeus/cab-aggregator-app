package com.modsen.driverservice.repository;

import com.modsen.driverservice.model.Driver;
import com.modsen.driverservice.model.enums.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findFirstByStatus(DriverStatus status);

    Optional<Driver> findByIdAndStatusIsNot(Long id, DriverStatus status);

    Page<Driver> findAllByStatusIsNot(Pageable pageable, DriverStatus status);

    boolean existsByPhoneAndStatusIsNot(String phone, DriverStatus status);

    boolean existsByEmailAndStatusIsNot(String email, DriverStatus status);
}
