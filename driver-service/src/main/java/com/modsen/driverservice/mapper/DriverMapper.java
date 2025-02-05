package com.modsen.driverservice.mapper;

import com.modsen.driverservice.dto.request.DriverRequest;
import com.modsen.driverservice.dto.response.DriverResponse;
import com.modsen.driverservice.dto.response.DriversPagedResponse;
import com.modsen.driverservice.model.Car;
import com.modsen.driverservice.model.Driver;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        componentModel = MappingConstants.ComponentModel.SPRING,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface DriverMapper {

    Driver toDriver(DriverRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cars", ignore = true)
    Driver updateDriver(DriverRequest dto, @MappingTarget Driver driver);

    @Mapping(target = "carIds", source = "cars", qualifiedByName = "carsToIds")
    DriverResponse toResponse(Driver driver);

    @Named("mapDriversPage")
    List<DriverResponse> toList(Page<Driver> drivers);

    @Mapping(target = "drivers", source = "responsePage", qualifiedByName = "mapDriversPage")
    @Mapping(target = "totalPages", source = "responsePage.totalElements")
    DriversPagedResponse mapDriversToPagedResponse(int currentPage, Page<Driver> responsePage);

    @Named(value = "toRegistered")
    @Mapping(target = "carIds", ignore = true)
    DriverResponse toRegistered(Driver driver);

    @Named("carsToIds")
    default List<Long> carsToIds(Set<Car> cars) {
        return cars.stream()
                .map(Car::getId)
                .toList();
    }

}
