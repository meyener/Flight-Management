package com.flightinquiry.scheduled;

import com.flightinquiry.model.request.FlightRequest;
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

        List<FlightRequest> flightRequests= Arrays.asList(
                new FlightRequest(),new FlightRequest(),new FlightRequest()
        );

        for (FlightRequest flightDto:flightRequests) {
            flightService.saveFlight(flightDto,null);

        }
    }
}
