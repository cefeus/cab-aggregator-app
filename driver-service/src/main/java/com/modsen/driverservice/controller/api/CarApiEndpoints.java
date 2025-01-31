package com.modsen.driverservice.controller.api;

import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cars controller",
        description = "Contains CRUD operations for interaction with cars data")
public interface CarApiEndpoints {

    @Operation(description = "Creates new car with provided data")
    CarResponse createCar(@RequestBody @Valid CarRequest dto);

    @Operation(description = "Updates all provided fields for car with provided id")
    CarResponse updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest dto);

    @Operation(description = "Retrieving all cars via paged result that are currently active")
    CarsPagedResponse findAllActive(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieving all cars via paged result. Contains deleted records")
    CarsPagedResponse findAll(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieves car via id if it exists")
    CarResponse findById(@PathVariable Long id);

    @Operation(description = "Retrieves car via car number if it exists")
    CarResponse findByCarNumber(@PathVariable String number);

    @Operation(description = "Deletes car via id if it exists")
    void delete(@PathVariable Long id);
}
