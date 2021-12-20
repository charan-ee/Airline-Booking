package com.everest.airline.model;

import org.springframework.format.annotation.DateTimeFormat;

public class Flight {
    private Long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private Integer totalSeats;
    private int availableSeats;
    private Integer occupiedSeats;
    private Integer economySeats;
    private Integer firstClass;
    private Integer secondClass;


    public Flight(Long number, String source, String destination, String date, String departureTime, String arrivalTime, Integer totalSeats, Integer occupiedSeats,
                  Integer ecoSeats, Integer secSeats, Integer firstSeats) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.occupiedSeats = occupiedSeats;
        this.economySeats = ecoSeats;
        this.firstClass = firstSeats;
        this.secondClass = secSeats;
    }

    public Integer getSecondClass() {
        return secondClass;
    }

    public Integer getFirstClass() {
        return firstClass;
    }

    public Integer getEconomySeats() {
        return economySeats;
    }

    public Integer getOccupiedSeats() {
        return occupiedSeats;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public Long getNumber() {
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

    public Integer getTotalSeats() {
        return totalSeats;
    }
}
