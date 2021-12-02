package com.everest.airline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class SearchController {

    @RequestMapping(value = "/")
    public String home(Model model) {
        String dateToday = LocalDate.now().toString();
        model.addAttribute("depDate", dateToday);
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String departureDate, String from, String to, Model model) {
        List<Flight> flights = searchFlights(departureDate, from, to, Data.flights);
        model.addAttribute("flights", flights);
        model.addAttribute("depDate", departureDate);
        return "search";
    }

    private List<Flight> searchFlights(String departureDate, String origin, String destination, List<Flight> flights){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate date = LocalDate.parse(departureDate, formatter);

        List<Flight> flightStream = flights.stream()
                .filter(flight -> (Objects.equals(flight.getSource(), origin) && Objects.equals(flight.getDestination(), destination) && flight.getDepartureDate().equals(date) ))
                .collect(Collectors.toList());
        return flightStream;
    }
}
