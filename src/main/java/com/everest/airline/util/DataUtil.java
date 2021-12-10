package com.everest.airline.util;


import com.everest.airline.model.Flight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUtil {
    BufferedReader fileReader;
    private final String dataPath = "/Users/charan/Documents/Task/airlines/src/main/java/com/everest/airline/util/Data.txt";
    private List<Flight> flightList;

    public DataUtil(){

    }

    public List<Flight> readFlights(String departureDate, String origin, String destination) throws IOException{
        try {
            fileReader = new BufferedReader(new FileReader(dataPath));
            String line;
            flightList = new ArrayList<>();
            while((line = fileReader.readLine()) != null){
                String[] values = line.split(",");
                if(values[1].equals(origin) && values[2].equals(destination) && values[3].equals(departureDate)) {
                    Flight flight = new Flight(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]));
                    flightList.add(flight);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return flightList;
    }
}
