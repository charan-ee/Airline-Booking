package com.everest.airline.controller;

import com.everest.airline.Data;
import com.everest.airline.model.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.everest.airline.service.SearchService;

@Controller
public class SearchController {
    private SearchService searchService;
    @RequestMapping(value = "/")
    public String home(Model model) {
        String dateToday = LocalDate.now().toString();
        model.addAttribute("depDate", dateToday);
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String departureDate, String from, String to, Model model) {
        List<Flight> flights = searchService.searchFlights(departureDate, from, to, Data.flights);
        model.addAttribute("flights", flights);
        model.addAttribute("depDate", departureDate);
        return "search";
    }
}
