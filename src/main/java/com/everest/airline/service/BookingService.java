package com.everest.airline.service;

import com.everest.airline.model.Flight;
import com.everest.airline.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookingService {
    @Autowired
    DataUtil dataUtil;

    public void bookFlight(Flight flight, Integer passengerCount) throws IOException {
        dataUtil.writeToFlight(flight, passengerCount);
    }
}
