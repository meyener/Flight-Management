package com.flightinquiry.controller;

import com.flightinquiry.model.dto.AirportDto;
import com.flightinquiry.model.request.AirportRequest;
import com.flightinquiry.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public List<AirportDto> getAllAirports(){
        return airportService.getAllAirports();
    }

    @GetMapping("/airport/{id}")
    public AirportDto findAirportById(@PathVariable Long id){
        return airportService.findAirportById(id);
    }

    @PostMapping("/saveAirport")
    public AirportDto saveAirport(@Valid @RequestBody AirportRequest airport){
        return airportService.saveAirport(airport);
    }
    @DeleteMapping("/deleteAirports/{id}")
    public void deleteAirportById(@PathVariable Long id){
        airportService.deleteAirportById(id);
    }

}
