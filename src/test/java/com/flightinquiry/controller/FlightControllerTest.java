package com.flightinquiry.controller;

import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.service.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;

class FlightControllerTest {
    @Mock
    FlightService flightService;
    @InjectMocks
    FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlights() {
        when(flightService.getAllFlights()).thenReturn(List.of(new FlightDto()));

        ResponseEntity<List<FlightDto>> result = flightController.getAllFlights();
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindFlightById() {
        when(flightService.findFlightById(anyLong())).thenReturn(new FlightDto());
        FlightDto result = flightController.findFlightById(1L);
        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveFlight() {
        when(flightService.saveFlight(any())).thenReturn(new FlightDto());
        FlightDto result = flightController.saveFlight(new Flight());
        Assertions.assertNotNull(result); }

    @Test
    void testUpdateFlight() {
        when(flightService.updateFlight(any(), anyLong())).thenReturn(new FlightDto());
        FlightDto result = flightController.updateFlight(new Flight(),1L);
        Assertions.assertNotNull(result);    }

    @Test
    void testDeleteFlightById() {
        flightController.deleteFlightById(1L);
    }
}
