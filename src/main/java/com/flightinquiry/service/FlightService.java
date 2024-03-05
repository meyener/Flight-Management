package com.flightinquiry.service;

import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;

import java.util.List;

public interface FlightService {

    List<FlightDto> getAllFlights();
    FlightDto findFlightById(Long id);
    FlightDto saveFlight(Flight flight);
    FlightDto updateFlight(Flight flight, Long id);
    void deleteFlightById(Long id);

}
