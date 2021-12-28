package com.everest.airline.model;

import com.everest.airline.service.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class Flight {
    private Long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private Integer totalSeats;
    private Integer occupiedSeats = 0;
    private Integer economySeats;
    private Integer occupiedEconomySeats;
    private Integer firstClass;
    private Integer occupiedFirstClass;
    private Integer secondClass;
    private Integer occupiedSecondClass;
    private Integer baseFareEconomy;
    private Integer baseFareSecondClass;
    private Integer baseFareFirstClass;
    PricingStrategy pricingStrategy;
    public Double totalPrice = 0.0;


    public Flight(Long number, String source, String destination, String date, String departureTime, String arrivalTime, Integer totalSeats,
                  Integer ecoSeats, Integer occupiedEconomySeats, Integer secSeats, Integer occupiedSecondClass, Integer firstSeats, Integer occupiedFirstClass, Integer baseFareEconomy, Integer baseFareSecondClass, Integer baseFareFirstClass) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = date;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.economySeats = ecoSeats;
        this.firstClass = firstSeats;
        this.secondClass = secSeats;
        this.occupiedEconomySeats = occupiedEconomySeats;
        this.occupiedSecondClass = occupiedSecondClass;
        this.occupiedFirstClass = occupiedFirstClass;
        this.baseFareEconomy = baseFareEconomy;
        this.baseFareFirstClass = baseFareFirstClass;
        this.baseFareSecondClass = baseFareSecondClass;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getBaseFareEconomy() {
        return baseFareEconomy;
    }

    public Integer getBaseFareSecondClass() {
        return baseFareSecondClass;
    }

    public Integer getBaseFareFirstClass() {
        return baseFareFirstClass;
    }

    public void setOccupiedEconomySeats(Integer economySeats) {
        this.occupiedEconomySeats += economySeats;
    }

    public void setOccupiedFirstClass(Integer firstClass) {
        this.occupiedFirstClass += firstClass;
    }

    public void setOccupiedSeats(Integer occupiedSeats) {
        this.occupiedSeats += occupiedSeats;
    }

    public void setOccupiedSecondClass(Integer secondClass) {
        this.occupiedSecondClass += secondClass;
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

    public Integer getOccupiedEconomySeats() {
        return occupiedEconomySeats;
    }

    public Integer getOccupiedFirstClass() {
        return occupiedFirstClass;
    }

    public Integer getOccupiedSecondClass() {
        return occupiedSecondClass;
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

    public List<Integer> getTotalAndOccupiedSeatsByClass(String seatType){
        switch (FlightSeatType.valueOf(seatType)){
            case ECONOMIC:
                return List.of(economySeats,occupiedEconomySeats);
            case SECOND_CLASS:
                return List.of(secondClass, occupiedSecondClass);
        }
        return List.of(firstClass, occupiedFirstClass);
    }

    public Integer getBasePriceByClass(String seatType){
        switch (FlightSeatType.valueOf(seatType)){
            case ECONOMIC:
                return baseFareEconomy;
            case SECOND_CLASS:
                return baseFareSecondClass;
        }
        return baseFareFirstClass;
    }

    public void calculateTicketsPriceBySeatType(String seatType, Integer passengerCount){
        List<Integer> totalAndOccupiedSeats = getTotalAndOccupiedSeatsByClass(seatType);
        Integer baseFareByClass = getBasePriceByClass(seatType);

        if(totalAndOccupiedSeats.get(1) < ((30*totalAndOccupiedSeats.get(0))/100))
            pricingStrategy = PricingStrategy.defaultPrice(passengerCount);
        if(totalAndOccupiedSeats.get(1) > ((30*totalAndOccupiedSeats.get(0))/100) && totalAndOccupiedSeats.get(1) < ((50*totalAndOccupiedSeats.get(0))/100))
            pricingStrategy = PricingStrategy.priceCategoryOne(passengerCount, baseFareByClass);
        if(totalAndOccupiedSeats.get(1) > ((50*totalAndOccupiedSeats.get(0))/100) && totalAndOccupiedSeats.get(1) < ((75*totalAndOccupiedSeats.get(0))/100))
            pricingStrategy = PricingStrategy.priceCategoryTwo(passengerCount, baseFareByClass);
        if(totalAndOccupiedSeats.get(1) > ((75*totalAndOccupiedSeats.get(0))/100) && totalAndOccupiedSeats.get(1) < ((100*totalAndOccupiedSeats.get(0))/100))
            pricingStrategy = PricingStrategy.priceCategoryThree(passengerCount, baseFareByClass);
        totalPrice = pricingStrategy.calculatePrice(baseFareByClass);
    }

    @Override
    public String toString(){
        return String.valueOf(number)+','+source+','+destination+','+departureDate+','+departureTime+','+arrivalTime+','+totalSeats+','+
                economySeats+','+occupiedEconomySeats+','+secondClass+','+occupiedSecondClass+','+firstClass+','+occupiedFirstClass
                +','+baseFareEconomy+','+baseFareSecondClass+','+baseFareFirstClass;
    }
}
