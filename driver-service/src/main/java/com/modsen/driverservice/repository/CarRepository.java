package com.modsen.driverservice.repository;

import com.modsen.driverservice.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findFirstByIsActiveIsTrue();

    Optional<Car> findByIdAndIsActiveIsTrue(Long id);

    Optional<Car> findByCarNumberAndIsActiveIsTrue(String carNumber);

    Page<Car> findAllByIsActiveIsTrue(Pageable pageable);

    boolean existsByCarNumberAndIsActiveIsTrue(String carNumber);

}
