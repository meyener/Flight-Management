package com.flightinquiry.scheduled;

import com.flightinquiry.model.entity.Flight;
import com.flightinquiry.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledJob {

    private final FlightService flightService;

    @Scheduled(cron = "000**?")
    public void scheduledMethod(){

        List<Flight> flightRequests= Arrays.asList(
                new Flight(),new Flight(),new Flight()
        );

        for (Flight flight:flightRequests) {
            flightService.saveFlight(flight);

        }
    }
}
