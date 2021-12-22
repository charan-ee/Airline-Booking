package com.everest.airline.service;

import com.everest.airline.model.Flight;
import com.everest.airline.model.FlightSeatType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BookingService {

    public void bookFlight(Flight flight, Integer passengerCount, String seatType) {
        writeFlights(flight, passengerCount, seatType);
    }

    private void writeFlights(Flight flight, Integer passengerCount, String seatType){
        File flightDir = new File("/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/flights");
        List<File> files = List.of(Objects.requireNonNull(flightDir.listFiles()));
        List<File> updateFlightDir = files.stream().filter(file -> file.getName()
                .startsWith(String.valueOf(flight.getNumber()))).collect(Collectors.toList());
        Long flightId = flight.getNumber();
        try{
            FileReader reader = new FileReader(updateFlightDir.get(0));
            StringBuilder updatedLine = new StringBuilder();
            String s;
            try (BufferedReader br = new BufferedReader(reader)){
                while ((s=br.readLine()) != null){
                    String[] values = s.split(",");
                    Flight flight1 = new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]), Integer.parseInt(values[13]), Integer.parseInt(values[14]), Integer.parseInt(values[15]));
                    Long flightToUpdateId = flight1.getNumber();
                    if(flightToUpdateId.equals(flightId)){
                        Flight updatedFlight = updateFlightSeatAvailability(flight1, seatType, passengerCount);
                        updatedLine.append(updatedFlight);
                    }
                }
                FileWriter writer = new FileWriter(updateFlightDir.get(0));
                writer.write(updatedLine.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Flight updateFlightSeatAvailability(Flight flight, String seatType, Integer passengerCount) {
        switch (FlightSeatType.valueOf(seatType)){
            case ECONOMIC:
                flight.setOccupiedEconomySeats(passengerCount);
                return flight;
            case SECOND_CLASS:
                flight.setOccupiedSecondClass(passengerCount);
                return flight;
            case FIRST_CLASS:
                flight.setOccupiedFirstClass(passengerCount);
                return flight;
        }
        return flight;
    }
}
