package com.modsen.rideservice.mapper;

import com.modsen.rideservice.dto.request.RideRequest;
import com.modsen.rideservice.dto.response.RidePagedResponse;
import com.modsen.rideservice.dto.response.RideResponse;
import com.modsen.rideservice.model.Ride;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface RideMapper {

    RideResponse toResponse(Ride ride);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "driverId", source = "request.driverId", qualifiedByName = "mapToUUID")
    @Mapping(target = "passengerId", source = "request.passengerId", qualifiedByName = "mapToUUID")
    Ride toEntity(RideRequest request);

    @Mapping(target = "rides", source = "responsePage", qualifiedByName = "mapRidesPage")
    @Mapping(target = "totalPages", source = "responsePage.totalElements")
    RidePagedResponse mapRidesToPagedResponse(int currentPage, Page<Ride> responsePage);

    @Named("mapRidesPage")
    List<RideResponse> toList(Page<Ride> cars);

    @Named("mapToUUID")
    default UUID mapToUUID(String str) {
        return UUID.fromString(str);
    }
}
