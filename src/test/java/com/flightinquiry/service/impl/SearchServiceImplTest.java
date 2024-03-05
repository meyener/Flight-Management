package com.flightinquiry.service.impl;

import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.when;

class SearchServiceImplTest {
    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    SearchServiceImpl searchServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchFlightsWithParams() {
        LocalDateTime departureDate=LocalDateTime.of(2024,Month.MARCH,5,0,0);
        Flight flight=new Flight();
        List<Flight> flights=List.of(flight);
        Airport departureAirport=new Airport();
        Airport arrivalAirport=new Airport();

        flight.setId(1L);
        flight.setDepartureDate(LocalDateTime.of(2024,Month.JANUARY,5,0,0));
        flight.setReturnDate(LocalDateTime.of(2025,Month.MARCH,5,0,0));
        departureAirport.setId(1L);
        arrivalAirport.setId(1L);


        when(flightRepository.findFlightsByDepartureAirportAndArrivalAirport(departureAirport,arrivalAirport)).thenReturn(flights);
        List<Flight> searchFlightsWithParams=searchServiceImpl.searchFlightsWithParams(departureDate,null,departureAirport,arrivalAirport);

        Assertions.assertNotNull(searchFlightsWithParams);
         }

    @Test
    void testSearchFlightsWithParams_() {
        LocalDateTime departureDate=LocalDateTime.of(2024,Month.MARCH,5,0,0);
        LocalDateTime returnDate=LocalDateTime.of(2025,Month.MARCH,5,0,0);
        Flight flight=new Flight();
        List<Flight> flights=List.of(flight);
        Airport departureAirport=new Airport();
        Airport arrivalAirport=new Airport();

        flight.setId(1L);
        departureAirport.setId(1L);
        arrivalAirport.setId(1L);
        flight.setDepartureDate(LocalDateTime.of(2025,Month.MAY,5,0,0));
        flight.setReturnDate(LocalDateTime.of(2025,Month.JULY,5,0,0));
        departureAirport.setId(1L);
        arrivalAirport.setId(1L);


        when(flightRepository.findFlightsByDepartureAirportAndArrivalAirport(arrivalAirport,departureAirport)).thenReturn(flights);
        List<Flight> searchFlightsWithParams=searchServiceImpl.searchFlightsWithParams(departureDate,returnDate,departureAirport,arrivalAirport);

        Assertions.assertNotNull(searchFlightsWithParams);
    }
}
