package com.flightinquiry.controller;

import com.flightinquiry.model.dto.FlightDto;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/allflights")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/flight/{id}")
    public FlightDto findFlightById(@PathVariable Long id){
        return flightService.findFlightById(id);
    }

    @PostMapping("/saveflight")
    public FlightDto saveFlight(@Valid @RequestBody Flight flight){
        return flightService.saveFlight(flight);
    }
    @PostMapping("/updateflight")
    public FlightDto updateFlight(@Valid @RequestBody Flight flight, @RequestParam Long id){
        return flightService.updateFlight(flight,id);
    }

    @DeleteMapping("/deleteFlight/{id}")
    public void deleteFlightById(@PathVariable Long id){
        flightService.deleteFlightById(id);
    }

}
