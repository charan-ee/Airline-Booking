package com.everest.airline.controller;

import com.everest.airline.model.Flight;
import com.everest.airline.service.FlightServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightsController {

    private final FlightServiceImpl flightService;

    public FlightsController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }


    @GetMapping
    public List<Flight> getFlights() throws IOException {
        return flightService.findAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable("id") Long flightId) throws IOException {
        return flightService.findFlightById(flightId);
    }

    @PostMapping
    public Long create(@RequestBody Flight flight) throws IOException {
        Long newFlightId = flightService.getLastFlightId() + 1;
        flight.setNumber(newFlightId);
        flightService.addFlight(flight);
        return newFlightId;
    }

    @PutMapping("/{id}")
    public Flight update(@PathVariable("id") Long flightId, @RequestBody Flight flightBody) throws IOException {
        flightService.updateFlightById(flightId, flightBody);
        return flightService.findFlightById(flightId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long flightId) throws IOException {
        flightService.deleteFlightById(flightId);
    }

}
