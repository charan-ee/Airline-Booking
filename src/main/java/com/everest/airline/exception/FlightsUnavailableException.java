package com.everest.airline.exception;

public class FlightsUnavailableException extends RuntimeException {
    public FlightsUnavailableException(String message){
        super(message);
    }
}
