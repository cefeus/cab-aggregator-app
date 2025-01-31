package com.modsen.driverservice.mapper;

import com.modsen.driverservice.dto.request.CarRequest;
import com.modsen.driverservice.dto.response.CarResponse;
import com.modsen.driverservice.dto.response.CarsPagedResponse;
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
public interface CarMapper {

    Car toCar(CarRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "drivers", ignore = true)
    Car updateCar(CarRequest dto, @MappingTarget Car car);

    @Mapping(target = "driverIds", source = "drivers", qualifiedByName = "driversToIds")
    CarResponse toResponse(Car car);

    @Mapping(target = "cars", source = "responsePage", qualifiedByName = "mapCarsPage")
    @Mapping(target = "totalPages", source = "responsePage.totalElements")
    CarsPagedResponse mapCarsToPagedResponse(int currentPage, Page<Car> responsePage);

    @Named("mapCarsPage")
    List<CarResponse> toList(Page<Car> cars);

    @Named(value = "toRegistered")
    @Mapping(target = "driverIds", ignore = true)
    CarResponse toRegistered(Car car);

    @Named("driversToIds")
    default List<Long> carsToIds(Set<Driver> drivers) {
        return drivers.stream()
                .map(Driver::getId)
                .toList();
    }
}
