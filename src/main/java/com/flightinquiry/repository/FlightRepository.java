package com.flightinquiry.repository;


import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findFlightsByDepartureAirportAndArrivalAirport(Airport departureAirport,Airport arrivalAirport);
}
