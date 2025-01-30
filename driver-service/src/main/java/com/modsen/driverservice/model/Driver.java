package com.modsen.driverservice.model;

import com.modsen.driverservice.converter.DriverStatusConverter;
import com.modsen.driverservice.converter.SexConverter;
import com.modsen.driverservice.model.enums.DriverStatus;
import com.modsen.driverservice.model.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
import java.util.Set;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
@Table(name = "drivers")
@DynamicInsert
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name ="phone")
    private String phone;
    @Column(name ="email")
    private String email;
    @Column(name ="rating")
    private String rating;
    @Convert(converter = DriverStatusConverter.class)
    @Column(name ="status")
    private DriverStatus status;
    @Convert(converter = SexConverter.class)
    @Column(name = "sex")
    private Sex sex;

    @Column(name ="cars")
    @ManyToMany
    @JoinTable(
            name = "cars_drivers",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private Set<Car> cars;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;
}
