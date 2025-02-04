package com.modsen.driverservice.validation;

import com.modsen.driverservice.model.Car;

public interface CarValidator {

    void checkExistsByNumber(String number);

    Car getOrThrow(Long id);

    Car getOrThrow(String carNumber);
}
