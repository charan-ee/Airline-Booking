package com.everest.airline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class SearchController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String from, String to, Model model) {
        List<Flight> flights = searchFlights(from, to, Data.flights);
        model.addAttribute("flights", flights);
        return "search";
    }

    private List<Flight> searchFlights(String origin, String destination, List<Flight> flights){
        List<Flight> flightStream = flights.stream()
                .filter(flight -> (Objects.equals(flight.getSource(), origin) && Objects.equals(flight.getDestination(), destination)))
                .collect(Collectors.toList());
        System.out.println(flightStream);
        return flightStream;
    }
}
