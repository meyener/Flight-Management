package com.flightinquiry.service;

import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.mapper.AirportMapper;
import com.flightinquiry.model.request.AirportRequest;
import com.flightinquiry.repository.AirportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<AirportDto> getAllAirports(){
        return airportRepository.findAll().stream()
                .map(AirportMapper::toDto)
                .toList();
    }

    public List<Airport> getAllAirportsWitId(){
        return airportRepository.findAll();
    }

    public AirportDto findAirportById(Long id){
        Optional<Airport> airport=airportRepository.findById(id);

        if (airport.isPresent()){
            return AirportMapper.toDto(airport.get());
        }else{
            throw new EntityNotFoundException("There's no airport for this id : " + id);
        }
    }

    public AirportDto saveAirport(AirportRequest airportRequest) {
        Optional<Airport> optionalAirport = airportRepository.findByCity(airportRequest.getCity());

        if (optionalAirport.isEmpty()){
            Airport airport=new Airport();
            airport.setCity(airportRequest.getCity());

            Airport savedAirport = airportRepository.save(airport);
            return AirportMapper.toDto(savedAirport);
        }else{
            throw new RuntimeException("This object is Exist !!!");
        }

    }

    public void deleteAirportById(Long id) {
        airportRepository.deleteById(id);
    }

}
