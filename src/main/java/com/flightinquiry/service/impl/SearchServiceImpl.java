package com.flightinquiry.service.impl;

import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.repository.FlightRepository;
import com.flightinquiry.service.SearchService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final FlightRepository flightRepository;

    public List<Flight> searchFlightsWithParams(
            LocalDateTime departureDate,
            @Nullable LocalDateTime returnDate,
            Airport departureAirport,
            Airport arrivalAirport) {


        List<Flight> flights = flightRepository
                .findFlightsByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport)
                .stream()
                .filter(flight -> !flight.getDepartureDate().isBefore(departureDate) ||
                        flight.getDepartureDate().isEqual(departureDate))
                .collect(Collectors.toList());

        if (returnDate == null) {
            return flights;
        } else {


            List<Flight> returnFlights = flightRepository
                    .findFlightsByDepartureAirportAndArrivalAirport(arrivalAirport,departureAirport )
                    .stream()
                    .filter(flight -> !flight.getDepartureDate().isAfter(returnDate) ||
                            flight.getDepartureDate().isEqual(returnDate))
                    .toList();

            flights.addAll(returnFlights);

            return flights;

        }

    }
}
