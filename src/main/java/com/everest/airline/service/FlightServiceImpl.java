package com.everest.airline.service;

import com.everest.airline.model.Flight;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FlightServiceImpl implements FlightService{

    String flightDataDir = "/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/flights";

    @Override
    public List<Flight> findAllFlights() throws IOException {
        List<Flight> flightList = new ArrayList<>();
        List<Path> flightDataList = Files.walk(Paths.get(flightDataDir)).filter(Files::isRegularFile).sorted().collect(Collectors.toList());
        for(Path flightData: flightDataList){
            String str = Files.readString(flightData);
            String[] values = str.split(",");
            flightList.add(new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]), Integer.parseInt(values[13]), Integer.parseInt(values[14]), Integer.parseInt(values[15])));
        }
        return flightList;
    }

    @Override
    public Flight findFlightById(Long flightId) throws IOException {
        Stream<Path> flightData = Files.walk(Paths.get(flightDataDir)).sorted();
        Path flightPath = flightData.filter(file -> file.getFileName().toString().startsWith(String.valueOf(flightId))).findFirst().orElse(null);
        String[] values = Files.readString(flightPath).split(",");
        return new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8]), Integer.parseInt(values[9]), Integer.parseInt(values[10]), Integer.parseInt(values[11]), Integer.parseInt(values[12]), Integer.parseInt(values[13]), Integer.parseInt(values[14]), Integer.parseInt(values[15]));
    }

    @Override
    public Long getLastFlightId() throws IOException {
        List<Flight> flightList = findAllFlights();
        Flight lastFlight = flightList.get(flightList.size()-1);
        return lastFlight.getNumber();
    }

    @Override
    public void addFlight(Flight flight) throws IOException {
        String newPath = flightDataDir+"/"+flight.getNumber()+".txt";
        Path newFlightPath = Paths.get(newPath);
        Files.write(newFlightPath, flight.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void updateFlightById(Long flightId, Flight flightBody) throws IOException {
        Flight existingFlight = findFlightById(flightId);
        Path existingFlightPath = Files.walk(Paths.get(flightDataDir)).sorted()
                .filter(file -> file.getFileName().toString().startsWith(String.valueOf(flightId)))
                .findFirst()
                .orElse(null);
        Files.write(existingFlightPath, flightBody.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void deleteFlightById(Long flightId) throws IOException{
        Path flightToDelete = Files.walk(Paths.get(flightDataDir))
                .filter(file -> file.getFileName().toString().equals(String.valueOf(flightId)+".txt"))
                .findFirst()
                .orElse(null);
        Files.delete(flightToDelete);
    }
}
