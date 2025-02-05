package com.modsen.rideservice.model;

import com.modsen.rideservice.converter.PaymentTypeConverter;
import com.modsen.rideservice.converter.RideStatusConverter;
import com.modsen.rideservice.converter.UuidConverter;
import com.modsen.rideservice.model.enums.PaymentType;
import com.modsen.rideservice.model.enums.RideStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
@Table(name = "rides")
@DynamicInsert
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Convert(converter = UuidConverter.class)
    @Column(name = "driver_id")
    private String driverId;

    @Convert(converter = UuidConverter.class)
    @Column(name = "passenger_id")
    private String passengerId;

    @Column(name = "price")
    private Double price;

    @Convert(converter = PaymentTypeConverter.class)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "from_address")
    private String from;

    @Column(name = "to_address")
    private String to;

    @Convert(converter = RideStatusConverter.class)
    @Column(name = "ride_status")
    private RideStatus rideStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;
}
