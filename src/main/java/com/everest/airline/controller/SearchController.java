package com.everest.airline.controller;

//import com.everest.airline.model.Data;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.everest.airline.service.SearchService;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping(value = "/")
    public String home(Model model) {
        String dateToday = LocalDate.now().toString();
        model.addAttribute("depDate", dateToday);
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String departureDate, String from, String to, Model model) throws IOException {
        List<Flight> flights = searchService.searchFlights(departureDate, from, to);
        model.addAttribute("flights", flights);
        model.addAttribute("depDate", departureDate);
        return "search";
    }
}
