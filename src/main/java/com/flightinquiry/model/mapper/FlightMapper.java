package com.flightinquiry.model.mapper;

import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;

public class FlightMapper {

    public static final FlightMapper INSTANCE=new FlightMapper();

    private FlightMapper(){}

    public FlightDto toDto(Flight flight) {

        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();

    }

}
