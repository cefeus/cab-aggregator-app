package com.modsen.rideservice.service.impl;

import com.modsen.rideservice.dto.geo.Coordinates;
import com.modsen.rideservice.service.GeocodeService;
import com.modsen.rideservice.service.OSRMRouteService;
import com.modsen.rideservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final OSRMRouteService routeService;
    private final GeocodeService geocodeService;
    private final double PRICE_MULTIPLIER = 1.53;

    @Override
    public double calculatePrice(String from, String to) {
        var addressFrom = geocodeService.getGeocode(from);
        addDelay();
        var addressTo = geocodeService.getGeocode(to);

        Coordinates fromCoords = new Coordinates(trimCoords(addressFrom.lat()), trimCoords(addressFrom.lon()));
        Coordinates toCoords = new Coordinates(trimCoords(addressTo.lat()), trimCoords(addressTo.lon()));

        double price = routeService.getRoute(fromCoords, toCoords).routes().get(0).distance() * PRICE_MULTIPLIER;
        return price;
    }

    private double trimCoords(double numb) {
        int temp = (int) (numb * 1000000.0);
        double shortDouble = ((double) temp) / 1000000.0;
        return shortDouble;
    }

    private void addDelay() {
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000L) {}
    }
}
