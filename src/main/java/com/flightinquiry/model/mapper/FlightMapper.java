package com.flightinquiry.model.mapper;

import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.request.FlightRequest;

public class FlightMapper {

    private FlightMapper(){}

    public static FlightDto toDto(Flight flight) {

        return FlightDto.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();

    }

    public static FlightDto toDto(FlightDto currentFlight,FlightRequest flight,Long id){

        if (flight.getArrivalAirport() != null) {
            currentFlight.setArrivalAirport(flight.getArrivalAirport());
        }
        if (flight.getDepartureAirport() != null) {
            currentFlight.setDepartureAirport(flight.getDepartureAirport());
        }
        if (flight.getDepartureDate() != null) {
            currentFlight.setDepartureDate(flight.getDepartureDate());
        }
        if (flight.getReturnDate() != null) {
            currentFlight.setReturnDate(flight.getReturnDate());
        }
        if (flight.getPrice() != null) {
            currentFlight.setPrice(flight.getPrice());
        }
        return currentFlight;
    }
    public static FlightRequest toRequest(FlightDto flight){

        return FlightRequest.builder()
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();
    }

}
