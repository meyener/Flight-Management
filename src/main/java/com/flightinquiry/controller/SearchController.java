package com.flightinquiry.controller;

import com.flightinquiry.model.entity.Airport;
import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.service.SearchService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v0/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<Flight> searchFlightsWithParams(@RequestParam LocalDateTime departureDate,
                                                @RequestParam  @Nullable LocalDateTime returnDate,
                                                @RequestParam Airport departureAirport,
                                                @RequestParam Airport arrivalAirport){
        return searchService.searchFlightsWithParams(departureDate,returnDate,departureAirport,arrivalAirport);
    }
}
