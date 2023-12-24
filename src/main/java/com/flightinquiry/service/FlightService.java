package com.flightinquiry.service;


import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.model.mapper.FlightMapper;
import com.flightinquiry.model.request.FlightRequest;
import com.flightinquiry.repository.AirportRepository;
import com.flightinquiry.repository.FlightRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<FlightDto> getAllFlights() {

        List<Flight> flightList = flightRepository.findAll();

        if (flightList != null) {

            return flightList.stream().map(FlightMapper::toDto).toList();
        } else {
            throw new RuntimeException("any flight couldnt find!! ");
        }

    }

    public FlightDto findFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);

        if (flight.isPresent()) {
            return FlightMapper.toDto(flight.get());
        } else {
            throw new EntityNotFoundException("There's no flight for this id : " + id);
        }
    }

    public FlightDto saveFlight(FlightRequest flight, @Nullable Long id) {

        Flight flight1 = Flight.builder()
                .departureAirport(airportRepository.findByCity(flight.getDepartureAirport()).get())
                .arrivalAirport(airportRepository.findByCity(flight.getArrivalAirport()).get())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();
        if (id!=null){
            flight1.setId(id);
        }

        return FlightMapper.toDto(flightRepository.save(flight1));
    }

    public FlightDto updateFlight(FlightRequest flight, Long id) {
        FlightDto currentFlight = findFlightById(id);
        FlightDto flightDto = FlightMapper.toDto(currentFlight, flight, id);
        return saveFlight(FlightMapper.toRequest(flightDto), id);
    }


    public void deleteFlightById(Long id) {
        findFlightById(id);
        flightRepository.deleteById(id);
    }


}
