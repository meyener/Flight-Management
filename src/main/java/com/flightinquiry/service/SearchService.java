package com.flightinquiry.service;

import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchService {

    List<Flight> searchFlightsWithParams(
            LocalDateTime departureDate,
            @Nullable LocalDateTime returnDate,
            Airport departureAirport,
            Airport arrivalAirport);
}
