package com.everest.airline.service;

import com.everest.airline.model.Flight;
import com.everest.airline.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingService {
    @Autowired
    DataUtil dataUtil;

    public void bookFlight(Flight flight, Integer passengerCount) throws IOException {
        writeFlights(flight, passengerCount);
    }

    private void writeFlights(Flight flight, Integer passengerCount){
        File flightDir = new File("/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/flights");
        List<File> files = List.of(flightDir.listFiles());
        List<File> updateFlightDir = files.stream().filter(file -> file.getName()
                .startsWith(String.valueOf(flight.getNumber()))).collect(Collectors.toList());
        Long flightId = flight.getNumber();
        try{
            FileReader reader = new FileReader(updateFlightDir.get(0));
            StringBuilder tempLine = new StringBuilder();
            StringBuilder updatedLine = new StringBuilder();
            String s;
            try (BufferedReader br = new BufferedReader(reader)){
                while ((s=br.readLine()) != null){
                    String[] values = s.split(",");
                    if(values[0].equals(String.valueOf(flightId))){
                        tempLine.append(s);
                        values[values.length-1] = String.valueOf(flight.getTotalSeats()-passengerCount);
                        updatedLine.append(String.join(",", values));
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
}
