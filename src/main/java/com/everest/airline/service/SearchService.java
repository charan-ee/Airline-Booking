package com.everest.airline.service;

import com.everest.airline.exception.FlightsUnavailableException;
import com.everest.airline.model.Flight;
import com.everest.airline.util.DataUtil;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SearchService {
    private DataUtil dataUtil = new DataUtil();
    public List<Flight> searchFlights(String departureDate, String origin, String destination) throws IOException {
        List<Flight> flights = readFlights(departureDate, origin, destination);
        if(flights.isEmpty())
            throw new FlightsUnavailableException("No flights found on your route");
        return flights;
    }

    private List<Flight> readFlights(String departureDate, String origin, String destination) throws IOException {
        File flightDir = new File("/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/flights");
        File[] files = flightDir.listFiles();
        List<Flight> flightList = new ArrayList<>();
        for(File file: files){
            if(file!=null){
                String readLine;
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    while ((readLine = fileReader.readLine()) != null) {
                        String[] values = readLine.split(",");
                        if (values[1].equals(origin) && values[2].equals(destination) && values[3].equals(departureDate)) {
                            Flight flight = new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]));
                            flightList.add(flight);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return flightList;
    }

}
