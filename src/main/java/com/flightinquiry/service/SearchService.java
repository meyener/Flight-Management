package com.flightinquiry.service;

import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.model.mapper.FlightMapper;
import com.flightinquiry.repository.AirportRepository;
import com.flightinquiry.repository.FlightRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<Flight> searchFlightsWithParams(
            LocalDateTime departureDate,
            @Nullable LocalDateTime returnDate,
            String departureAirport,
            String arrivalAirport) {
        Optional<Airport> departure = airportRepository.findByCity(departureAirport);
        Optional<Airport> arrival = airportRepository.findByCity(arrivalAirport);

        List<Flight> flights = flightRepository
                .findFlightsByDepartureAirportAndArrivalAirport(departure.get(), arrival.get());

        flights.stream()
                .filter(flight -> !flight.getDepartureDate().isBefore(departureDate) || flight.getDepartureDate().isEqual(departureDate))
                .map(FlightMapper::toDto)
                .collect(Collectors.toList());
        if (returnDate == null) {

            return flights;
        }else {

            List<Flight> returnFlights = flightRepository
                    .findFlightsByDepartureAirportAndArrivalAirport(arrival.get(),departure.get());

            returnFlights.stream()
                    .filter(flight -> !flight.getDepartureDate().isAfter(returnDate) || flight.getDepartureDate().isEqual(returnDate))
                    .map(FlightMapper::toDto)
                    .collect(Collectors.toList());

            flights.addAll(returnFlights);

            return flights;

        }

    }
}
