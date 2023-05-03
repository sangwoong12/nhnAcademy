package com.nhnacademy.flight.item;

public class Boarding {
    //reservation 정보
    private String reservedDate;

    //flight 정보
    private String departure;
    private String arrival;
    private int price;
    private String flightDate;

    //aircraft 정보
    private String kindOfAirCraft;
    private String airline;

    public Boarding(String reservedDate, String departure, String arrival, int price, String flightDate, String kindOfAirCraft, String airline) {
        this.reservedDate = reservedDate;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.flightDate = flightDate;
        this.kindOfAirCraft = kindOfAirCraft;
        this.airline = airline;
    }

    public String getReservedDate() {
        return reservedDate;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getPrice() {
        return price;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public String getKindOfAirCraft() {
        return kindOfAirCraft;
    }

    public String getAirline() {
        return airline;
    }
}
