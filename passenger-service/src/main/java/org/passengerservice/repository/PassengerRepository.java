package org.passengerservice.repository;


import com.modsen.passengerservice.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM passengers p WHERE phone_number = :number and is_active = true)", nativeQuery = true)
    boolean existsByPhoneNumberAndIsActiveIsTrue(@Param("number") String number);

    @Query(value = "UPDATE passengers p SET payment_type = :type WHERE id = :id", nativeQuery = true)
    void updatePaymentType(@Param("type") String paymentType, @Param("id") Long id);
    Page<Passenger> getAllByIsActiveIsTrue(Pageable pageable);

    Optional<Passenger> findByIdAndIsActiveIsTrue(Long id);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM passengers p WHERE email = :email and is_active = true)", nativeQuery = true)
    boolean existsByEmailAndIsActiveIsTrue(@Param("email")String email);
}
