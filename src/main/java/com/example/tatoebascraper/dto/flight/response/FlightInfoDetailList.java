package com.example.tatoebascraper.dto.flight.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FlightInfoDetailList {
    public String departureTime;
    public String departureDate;
    public String arrivalTime;
    public String arrivalDate;
    public String departureTimeUTC;
    public String departureDateUTC;
    public Object departureStation;
    public ArrayList<String> departureCityName;
    public String departureAirportCode3A;
    public Object arrivalStation;
    public ArrayList<String> arrivalCityName;
    public String arrivalAirportCode3A;
    public String marketingCompany;
    public String operatingCompany;
    public String flightNumber;
    public String typeOfAircraft;
    public int stopsCount;
    public String legDuration;
    public int carrierCode;
    public ArrayList<FareCityPairList> fareCityPairList;
    public Object transitFlightDetailList;
}
