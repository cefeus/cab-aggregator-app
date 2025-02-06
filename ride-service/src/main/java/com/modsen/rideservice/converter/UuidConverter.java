package com.modsen.rideservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class UuidConverter implements AttributeConverter<String, UUID> {

    @Override
    public UUID convertToDatabaseColumn(String uuid) {
        return UUID.fromString(uuid);
    }

    @Override
    public String convertToEntityAttribute(UUID uuid) {
        return uuid.toString();
    }

}
