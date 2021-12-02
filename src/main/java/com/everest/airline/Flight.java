package com.everest.airline;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Flight {
    private long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate departureDate;

    public Flight(long number, String source, String destination, LocalDate date) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate date){
        departureDate = date;
    }

    public long getNumber() {
        return number;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

}
