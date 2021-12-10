package com.everest.airline.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private int occupiedSeats;

    public Flight(long number, String source, String destination, String date, String departureTime, String arrivalTime, int totalSeats) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
    }

    public String getDepartureDate() {
        return departureDate;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
