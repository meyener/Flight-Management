package com.flightinquiry.model.mapper;

import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;

//created this class because new features may append in future
public class AirportMapper {
    private AirportMapper(){}

    public static AirportDto toDto(Airport airport){
        return new AirportDto(airport.getId(),airport.getCity());
    }
}
