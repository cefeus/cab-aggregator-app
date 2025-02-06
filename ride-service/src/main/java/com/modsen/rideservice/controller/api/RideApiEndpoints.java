package com.modsen.rideservice.controller.api;

import com.modsen.rideservice.dto.request.RideRequest;
import com.modsen.rideservice.dto.response.RidePagedResponse;
import com.modsen.rideservice.dto.response.RideResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface RideApiEndpoints {

    @Operation(description = "Creates new ride with provided data")
    RideResponse createRide(@RequestBody @Valid RideRequest dto);

    @Operation(description = "Retrieving all rides via paged result")
    RidePagedResponse findAll(@PageableDefault(
            size = 10,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    );

    @Operation(description = "Retrieves ride via id if it exists")
    RideResponse findById(@PathVariable Long id);

    @Operation(description = "Retrieves ride via id if it exists")
    RideResponse findLastByDriverId(@PathVariable String id);

    @Operation(description = "Retrieves ride via id if it exists")
    RideResponse findLastByPassengerId(@PathVariable String id);

    @Operation(description = "Updates ride status to In Progress")
    void updateRideStatusToInProgress(Long id);

    @Operation(description = "Updates ride status to Finished")
    void updateRideStatusToFinished(Long id);

    @Operation(description = "Soft deletes ride")
    void delete(Long id);
}
