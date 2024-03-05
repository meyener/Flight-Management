package com.flightinquiry.service;

import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.request.AirportRequest;

import java.util.List;

public interface AirportService {
    List<AirportDto> getAllAirports();
    AirportDto findAirportById(Long id);
    AirportDto saveAirport(Airport airport);
    AirportDto updateAirport(Airport airport,Long id);
    void deleteAirportById(Long id);
}
