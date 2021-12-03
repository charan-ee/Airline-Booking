package com.everest.airline.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public Flight(long number, String source, String destination, LocalDate date, LocalTime departureTime, LocalTime arrivalTime) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
}
