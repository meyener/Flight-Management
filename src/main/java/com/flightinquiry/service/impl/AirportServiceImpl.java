package com.flightinquiry.service.impl;

import com.flightinquiry.exception.AirportException;
import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.mapper.AirportMapper;
import com.flightinquiry.repository.AirportRepository;
import com.flightinquiry.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportMapper airportMapper = AirportMapper.INSTANCE;

    private final AirportRepository airportRepository;

    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(airportMapper::toDto)
                .toList();
    }


    public AirportDto findAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            return airportMapper.toDto(airport.get());
        } else {
            throw new AirportException("There's no airport for this id : " + id);
        }
    }

    public AirportDto saveAirport(Airport airport) {
        if (airport == null) {
            throw new AirportException("Object is null");
        }
        Airport save = airportRepository.save(airport);
        return airportMapper.toDto(save);
    }

    @Override
    public AirportDto updateAirport(Airport airport, Long id) {
        return airportRepository.findById(id).map(air -> {
            air.setCity(airport.getCity());
            return airportMapper.toDto(air);
        }).orElseThrow(() -> new AirportException("Airport couldn't update"));

    }


    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }

}
