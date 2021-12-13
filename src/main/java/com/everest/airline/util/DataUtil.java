package com.everest.airline.util;


import com.everest.airline.model.Flight;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataUtil {
    private final String dataPath = "/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/util/Data.txt";
    private List<Flight> flightList;
    public DataUtil(){

    }

    public List<Flight> readFlights(String departureDate, String origin, String destination) throws IOException{
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(dataPath));
            String readLine;
            flightList = new ArrayList<>();
            while((readLine = fileReader.readLine()) != null){
                String[] values = readLine.split(",");
                if(values[1].equals(origin) && values[2].equals(destination) && values[3].equals(departureDate)) {
                    Flight flight = new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]));
                    flightList.add(flight);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return flightList;
    }

    public void writeToFlight(Flight flight, Integer passengerCount) throws IOException{
        File file = new File("/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/util/Data.txt");
        Long flightId = flight.getNumber();
        try{
            FileReader reader = new FileReader(file);
            StringBuilder tempLine = new StringBuilder();
            StringBuilder updatedLine = new StringBuilder();
            StringBuilder buffer = new StringBuilder();

            String s;

            try (BufferedReader br = new BufferedReader(reader)){
                while ((s=br.readLine()) != null){
                    buffer.append(s).append(System.lineSeparator());
                    String[] values = s.split(",");
                    if(values[0].equals(String.valueOf(flightId))){
                        tempLine.append(s);
                        values[values.length-1] = String.valueOf(flight.getTotalSeats()-passengerCount);
                        updatedLine.append(String.join(",", values));
                    }
                }
                String fileContent = buffer.toString();
                fileContent = fileContent.replaceAll(tempLine.toString(),updatedLine.toString());
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
