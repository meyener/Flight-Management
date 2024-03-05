package com.flightinquiry.service.impl;

import com.flightinquiry.exception.FlightException;
import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {
    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    FlightServiceImpl flightServiceImpl;

    FlightDto flightDto;
    Flight flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        flightDto = new FlightDto();
        flight = new Flight();

        flightDto.setId(1L);
        flight.setId(1L);

        flight.setDepartureAirport(new Airport());
        flight.setArrivalAirport(new Airport());
    }

    @Test
    void testGetAllFlights() {
        List<FlightDto> resultList=List.of(flightDto);

        when(flightServiceImpl.getAllFlights()).thenReturn(resultList);
        Assertions.assertEquals(resultList.stream().findFirst().get().getId()
                , flightDto.getId());
    }

    @Test
    void testFindFlightById() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));
        FlightDto dto = flightServiceImpl.findFlightById(1L);

        Assertions.assertEquals(dto.getId(),flightDto.getId());

    }

    @Test
    void testFindFlightById_() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(FlightException.class, () -> flightServiceImpl.findFlightById(1L));

    }

    @Test
    void testSaveFlight() {

        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        FlightDto savedFlight = flightServiceImpl.saveFlight(flight);

        assertEquals(1L, savedFlight.getId());

    }

    @Test
    void testUpdateFlight() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));
        FlightDto savedFlight = flightServiceImpl.updateFlight(flight, 1L);

        assertEquals(1L, savedFlight.getId());
    }

    @Test
    void testUpdateFlight_whenThrowException() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(FlightException.class, () -> flightServiceImpl.updateFlight(flight, 1L));
    }

    @Test
    void testDeleteFlightById() {
        flightServiceImpl.deleteFlightById(1L);

    }
}

