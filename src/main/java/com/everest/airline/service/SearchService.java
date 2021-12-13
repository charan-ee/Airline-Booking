package com.everest.airline.service;

import com.everest.airline.exception.FlightsUnavailableException;
import com.everest.airline.model.Flight;
import com.everest.airline.util.DataUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SearchService {
    private DataUtil dataUtil = new DataUtil();
    public List<Flight> searchFlights(String departureDate, String origin, String destination) throws IOException {
        List<Flight> flights = dataUtil.readFlights(departureDate, origin, destination);
        if(flights.isEmpty())
            throw new FlightsUnavailableException("No flights found on your route");
        return flights;
    }
}
