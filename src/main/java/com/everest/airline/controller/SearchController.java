package com.everest.airline.controller;

import com.everest.airline.exception.FlightsUnavailableException;
import com.everest.airline.model.Flight;
import com.everest.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.everest.airline.service.SearchService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Autowired
    private BookingService bookingService;
    private List<Flight> flights;
    @RequestMapping(value = "/")
    public String home(Model model) {
        String dateToday = LocalDate.now().toString();
        model.addAttribute("depDate", dateToday);
        return "home";
    }

    @RequestMapping(value = "/search")
    public String search(String departureDate, String from, String to, Model model) throws IOException {
        flights = searchService.searchFlights(departureDate, from, to);
//        if(flights.isEmpty()){
//            model.addAttribute("noFlights", "No Flight Found");
//            System.out.println("returning home");
//            return "redirect:/";
//        }
        model.addAttribute("flights", flights);
        model.addAttribute("depDate", departureDate);
        return "search";
    }

    @RequestMapping(value = "/book")
    public String book(String flightId,String passengerCount, Model model) throws IOException {
        List<Flight> flightList = flights.stream().filter((Flight tempFlight)->{
            return tempFlight.getNumber().equals(Long.parseLong(flightId));
        }).collect(Collectors.toList());
        Flight flightToBook = flightList.get(0);
        bookingService.bookFlight(flightToBook, Integer.parseInt(passengerCount));

        model.addAttribute("passengerCount", passengerCount);
        model.addAttribute("flight", flightToBook);
        return "booking_page";
    }

    @ExceptionHandler({FlightsUnavailableException.class})
    public ModelAndView getNoFlightsAvailable(FlightsUnavailableException exception){
        return new ModelAndView("search", "error", exception.getMessage());
    }

}
