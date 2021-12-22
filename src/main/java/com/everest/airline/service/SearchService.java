package com.everest.airline.service;

import com.everest.airline.exception.FlightsUnavailableException;
import com.everest.airline.model.Flight;
import com.everest.airline.model.FlightSeatType;
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

    public List<Flight> searchFlights(String departureDate, String origin, String destination, String seatType, String passengerCount) throws IOException {
        List<Flight> flights = readFlights(departureDate, origin, destination, seatType, passengerCount);
        if(flights.isEmpty())
            throw new FlightsUnavailableException("No flights found on your route");
        return flights;
    }

    private List<Flight> readFlights(String departureDate, String origin, String destination, String seatType, String passengerCount ) throws IOException {
        File flightDir = new File("/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/flights");
        File[] files = flightDir.listFiles();
        List<Flight> flightList = new ArrayList<>();
        for(File file: files){
            if(file!=null){
                String readLine;
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    while ((readLine = fileReader.readLine()) != null) {
                        String[] values = readLine.split(",");
                        Flight flight = new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]), Integer.parseInt(values[13]), Integer.parseInt(values[14]), Integer.parseInt(values[15]));
                        String flightSource = flight.getSource();
                        String flightDestination = flight.getDestination();
                        String flightDepDate = flight.getDepartureDate();
                        if (flightSource.equals(origin) && flightDestination.equals(destination) && flightDepDate.equals(flightDepDate) && isSeatTypeAvailableForPassenger(flight, Integer.parseInt(passengerCount), seatType)) {
                            flight.calculateTicketsPriceBySeatType(seatType, Integer.parseInt(passengerCount));
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

    private boolean isSeatTypeAvailableForPassenger(Flight flight, Integer passengerCount, String seatType) {
        switch(FlightSeatType.valueOf(seatType)){
            case ECONOMIC:
                return (flight.getEconomySeats()-flight.getOccupiedEconomySeats()) >= passengerCount;
            case FIRST_CLASS:
                return (flight.getFirstClass()- flight.getOccupiedFirstClass()) >= passengerCount;
            case SECOND_CLASS:
                return (flight.getSecondClass()- flight.getOccupiedSecondClass()) >= passengerCount;
        }
        return false;
    }

}
