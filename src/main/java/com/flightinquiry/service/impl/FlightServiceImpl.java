package com.flightinquiry.service.impl;


import com.flightinquiry.exception.FlightException;
import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.model.mapper.FlightMapper;
import com.flightinquiry.repository.FlightRepository;
import com.flightinquiry.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log
public class FlightServiceImpl implements FlightService {
    private final FlightMapper flightMapper = FlightMapper.INSTANCE;

    private final FlightRepository flightRepository;

    public List<FlightDto> getAllFlights() {

        List<Flight> flightList = flightRepository.findAll();
        return flightList.stream().map(flightMapper::toDto).toList();

    }

    public FlightDto findFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            log.info("Flight found :"+ flight.get());
            return flightMapper.toDto(flight.get());
        } else {
            throw new FlightException("There's no flight for this id : " + id);
        }
    }

    public FlightDto saveFlight(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        log.info("Flight created :"+ savedFlight);
        return flightMapper.toDto(savedFlight);
    }

    public FlightDto updateFlight(Flight flight, Long id) {

        return flightRepository.findById(id).map(f -> {
            f.setPrice(flight.getPrice());
            f.setDepartureDate(flight.getDepartureDate());
            f.setReturnDate(flight.getReturnDate());
            f.setDepartureAirport(flight.getDepartureAirport());
            f.setArrivalAirport(flight.getArrivalAirport());
            log.info("Flight updated :"+ f);
            return flightMapper.toDto(f);
        }).orElseThrow(() -> new FlightException("Flight cannot found !!"));
    }


    public void deleteFlightById(Long id) {
            flightRepository.deleteById(id);
            log.info("Flight deleted id:"+ id);
    }


}
