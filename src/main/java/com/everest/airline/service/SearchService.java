package com.everest.airline.service;

import com.everest.airline.model.Flight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchService {
    public List<Flight> searchFlights(String departureDate, String origin, String destination, List<Flight> flights) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate date = LocalDate.parse(departureDate, formatter);

        List<Flight> flightStream = flights.stream()
                .filter(flight -> (Objects.equals(flight.getSource(), origin) && Objects.equals(flight.getDestination(), destination) && flight.getDepartureDate().equals(date)))
                .collect(Collectors.toList());
        return flightStream;
    }
}
