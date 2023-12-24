package com.flightinquiry.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;

    @OneToMany(mappedBy = "departureAirport",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Flight> flights;

    @OneToMany(mappedBy = "arrivalAirport",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Flight> flight;

}
