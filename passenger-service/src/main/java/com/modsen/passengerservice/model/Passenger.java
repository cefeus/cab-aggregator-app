package com.modsen.passengerservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "first_name", nullable = false)
    private String firstname;
    @Column(name = "last_name", nullable = false)
    private String lastname;
    @Column(name ="email")
    private String email;
    @Column(name="rating")
    private String rating;
    @Enumerated(EnumType.STRING)
    @Column(name ="payment_type")
    private PaymentType paymentType;
    @Column(name = "is_active")
    Boolean isActive;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;
}

