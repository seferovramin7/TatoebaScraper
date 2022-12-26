package com.example.tatoebascraper.dto.flight.response;

import java.util.ArrayList;


@lombok.Data
public class Data {
    public ArrayList<FlightsOfDeparture> flightsOfDeparture;
    public Object flightsOfArrival;
    public ArrayList<String> departureCityName;
    public ArrayList<String> arrivalCityName;
    public String uuid;
}


