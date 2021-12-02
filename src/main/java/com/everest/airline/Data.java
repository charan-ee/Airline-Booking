package com.everest.airline;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;

public class Data {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
    static LocalDate d = LocalDate.parse("03-12-2021", formatter);
    static LocalDate d1 = LocalDate.parse("02-12-2021", formatter);
    static List<Flight> flights = List.of(
            new Flight(1001, "Hyderabad", "Bangalore", d),
            new Flight(1002, "Bangalore", "Hyderabad", d1),
            new Flight(1003, "Goa", "Bangalore", d),
            new Flight(1004, "Bangalore", "Goa", d1),
            new Flight(1005, "Bangalore", "Hyderabad", d),
            new Flight(1006, "Hyderabad", "Bangalore", d1));

}
