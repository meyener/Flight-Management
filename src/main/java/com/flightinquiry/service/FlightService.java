package com.flightinquiry.service;


import com.flightinquiry.exception.FlightException;
import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.model.mapper.FlightMapper;
import com.flightinquiry.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightMapper flightMapper = FlightMapper.INSTANCE;

    private final FlightRepository flightRepository;

    public List<FlightDto> getAllFlights() {

        List<Flight> flightList = flightRepository.findAll();
        return flightList.stream().map(flightMapper::toDto).toList();

    }

    public FlightDto findFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            return flightMapper.toDto(flight.get());
        } else {
            throw new EntityNotFoundException("There's no flight for this id : " + id);
        }
    }

    public FlightDto saveFlight(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    public FlightDto updateFlight(Flight flight, Long id) {

        return saveFlight(flightRepository.findById(id).map(f -> {
            f.setPrice(flight.getPrice());
            f.setDepartureDate(flight.getDepartureDate());
            f.setReturnDate(flight.getReturnDate());
            f.setDepartureAirport(flight.getDepartureAirport());
            f.setArrivalAirport(flight.getArrivalAirport());
            return f;
        }).orElseThrow(() -> new FlightException("")));
    }


    public void deleteFlightById(Long id) {
        if (flightRepository.findById(id).isPresent()) {
            flightRepository.deleteById(id);
        } else {
            throw new FlightException("");
        }
    }


}
