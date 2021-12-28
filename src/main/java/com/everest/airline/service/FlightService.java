package com.everest.airline.service;

import com.everest.airline.model.Flight;

import java.io.IOException;
import java.util.List;

public interface FlightService {
    List<Flight> findAllFlights() throws IOException;

    Flight findFlightById(Long flightId) throws IOException;

    void addFlight(Flight flight) throws IOException;

    Long getLastFlightId() throws IOException;

    void deleteFlightById(Long flightId) throws IOException;

    void updateFlightById(Long flightId, Flight flightBody) throws IOException;
}

