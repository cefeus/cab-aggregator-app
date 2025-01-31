package com.modsen.driverservice.validation;

import com.modsen.driverservice.model.Driver;

public interface DriverValidator {

    void checkExistsByPhone(String phone);

    void checkExistsByEmail(String email);

    Driver getOrThrow(Long id);

}
