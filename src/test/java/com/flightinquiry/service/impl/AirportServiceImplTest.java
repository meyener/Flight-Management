package com.flightinquiry.service.impl;

import com.flightinquiry.exception.AirportException;
import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.mapper.AirportMapper;
import com.flightinquiry.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AirportServiceImplTest {
    @Mock
    AirportMapper airportMapper;
    @Mock
    AirportRepository airportRepository;
    @InjectMocks
    AirportServiceImpl airportServiceImpl;
    @Mock
    Airport mockAirport;
    @Mock
    AirportDto mockAirportDto;
    List<AirportDto> resultList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAirport=mock(Airport.class);
        mockAirportDto=mock(AirportDto.class);

        resultList=List.of(new AirportDto(1L, "city"));
        mockAirport.setCity("a");
        mockAirport.setId(1L);
        mockAirportDto.setCity("a");
        mockAirportDto.setId(1L);

    }

    @Test
    void testGetAllAirports() {

        when(airportServiceImpl.getAllAirports()).thenReturn(resultList);
        assertEquals(List.of(new AirportDto(1L, "city")), resultList);
    }

    @Test
    void testFindAirportById(){
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCity("a");
        airport.setFlight(Collections.emptyList());
        airport.setFlights(Collections.emptyList());
        AirportDto airportDto = new AirportDto(1L, "a");

        when(airportRepository.findById(anyLong())).thenReturn(Optional.of(airport));
        when(airportMapper.toDto(any(Airport.class))).thenReturn(airportDto);

        AirportDto airportById = airportServiceImpl.findAirportById(1L);

        assertEquals(1L, airportById.getId());
    }

    @Test
    void testFindAirportById_(){

        when(airportRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AirportException.class, () -> airportServiceImpl.findAirportById(1L));
    }

    @Test
    void testSaveAirport() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCity("a");
        airport.setFlight(Collections.emptyList());
        airport.setFlights(Collections.emptyList());

        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        AirportDto resultDto = airportServiceImpl.saveAirport(airport);

        assertEquals(1L, resultDto.getId());

    }

    @Test
    void testUpdateAirport() {
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setCity("a");
        airport.setFlight(Collections.emptyList());
        airport.setFlights(Collections.emptyList());

        when(airportRepository.findById(anyLong())).thenReturn(Optional.of(airport));
        AirportDto resultDto = airportServiceImpl.updateAirport(airport,1L);

        assertEquals(resultDto.getId(),1L);

    }
    @Test
    void testUpdateAirportNotFound() {
        // Mock data
        Long id = 1L;
        Airport airportToUpdate = new Airport();
        airportToUpdate.setId(id);
        airportToUpdate.setCity("Test City");

        when(airportRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AirportException.class, () -> airportServiceImpl.updateAirport(airportToUpdate, id));
    }

    @Test
    void testDeleteAirportById() {
        airportServiceImpl.deleteAirportById(Long.valueOf(1));
    }
    @Test
    void testSaveAirport_throws() {
        Airport airport=null;

        assertThrows(AirportException.class, () -> airportServiceImpl.saveAirport(airport));
    }
}
