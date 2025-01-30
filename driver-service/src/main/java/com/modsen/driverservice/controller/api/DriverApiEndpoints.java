package com.modsen.driverservice.controller.api;

import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Drivers controller",
        description = "Contains CRUD operations for interaction with drivers data")
public interface DriverApiEndpoints {

    @Operation(description = "Creates new driver with provided data")
    DriverResponse createDriver(@RequestBody @Valid DriverRequest dto);

    @Operation(description = "Updates all provided fields for driver with provided id")
    DriverResponse updateDriver(@PathVariable Long id, @RequestBody @Valid DriverRequest dto);

    @Operation(description = "Retrieving all drivers via paged result that are currently active")
    DriversPagedResponse findAllActive(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieving all drivers via paged result. Contains deleted records")
    DriversPagedResponse findAll(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieves drivers via id if it exists")
    DriverResponse findById(@PathVariable Long id);

    @Operation(description = "Deletes driver via id if it exists")
    void delete(@PathVariable Long id);

    @Operation(description = "Retrieves free drivers if it exists")
    DriverResponse getAnyFreeDriver();

    @Operation(description = "Adds new car to driver with provided data")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCar(@PathVariable Long id, @PathVariable String carNumber);

}
