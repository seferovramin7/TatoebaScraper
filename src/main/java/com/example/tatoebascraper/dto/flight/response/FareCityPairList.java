package com.example.tatoebascraper.dto.flight.response;

import java.util.ArrayList;

public class FareCityPairList {
    public String rateClass;
    public Object globalClass;
    public String bookingClass;
    public Object globalSubClass;
    public Object codeShare;
    public String company;
    public String directionCode;
    public double totalFare;
    public double allTotalFare;
    public double fare;
    public double taxes;
    public String currency;
    public int flightClassTypeCode;
    public boolean exists;
    public ArrayList<PassengerFareDetailList> passengerFareDetailList;
}
