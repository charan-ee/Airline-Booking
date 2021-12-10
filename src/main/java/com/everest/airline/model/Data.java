//package com.everest.airline.model;
//
//import com.everest.airline.model.Flight;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//public class Data {
//    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
//    static LocalDate d = LocalDate.parse("03-12-2021", formatter);
//    static LocalDate d1 = LocalDate.parse("02-12-2021", formatter);
//    static LocalTime t1 = LocalTime.parse("10:30");
//    static LocalTime t2 = LocalTime.parse("20:30");
//    public static List<Flight> flights = List.of(
//            new Flight(1001, "Hyderabad", "Bangalore", d, t1, t1.plus(1, ChronoUnit.HOURS)),
//            new Flight(1002, "Bangalore", "Hyderabad", d1, t2, t2.plus(1, ChronoUnit.HOURS)),
//            new Flight(1003, "Goa", "Bangalore", d, t1, t1.plus(2, ChronoUnit.HOURS)),
//            new Flight(1004, "Bangalore", "Goa", d1, t2, t2.plus(1, ChronoUnit.HOURS)),
//            new Flight(1005, "Bangalore", "Hyderabad", d, t2, t2.plus(1, ChronoUnit.HOURS)),
//            new Flight(1006, "Hyderabad", "Bangalore", d1, t2, t2.plus(1, ChronoUnit.HOURS)));
//
//}
