package com.flightinquiry.controller;

import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.service.AirportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class AirportControllerTest {
    @Mock
    AirportService airportService;
    @InjectMocks
    AirportController airportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAirports() {
        when(airportService.getAllAirports()).thenReturn(List.of(new AirportDto(1L, "city")));

        List<AirportDto> result = airportController.getAllAirports();
        Assertions.assertEquals(List.of(new AirportDto(1L, "city")), result);
    }

    @Test
    void testFindAirportById() {
        when(airportService.findAirportById(anyLong())).thenReturn(new AirportDto(1L, "city"));

        AirportDto result = airportController.findAirportById(1L);
        Assertions.assertEquals(new AirportDto(1L, "city"), result);
    }

    @Test
    void testSaveAirport() {
        when(airportService.saveAirport(any())).thenReturn(new AirportDto(1L, "city"));

        AirportDto result = airportController.saveAirport(new Airport(1L, "city",List.of(new Flight()),List.of(new Flight())));
                Assertions.assertEquals(new AirportDto(1L, "city"), result);
    }

    @Test
    void testUpdateAirport() {
        when(airportService.updateAirport(any(), anyLong())).thenReturn(new AirportDto(1L, "city"));

        AirportDto result = airportController.updateAirport(new Airport(), 1L);
        Assertions.assertEquals(new AirportDto(1L, "city"), result);
    }

    @Test
    void testDeleteAirportById() {
        airportController.deleteAirportById(1L);
    }
}

