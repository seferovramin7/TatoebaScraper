package com.example.tatoebascraper.dto.flight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlightRequest {

    public int adultCount;
    public String arrivalDate;
    public String arrivalPort;
    public int childCount;
    public String classType;
    public String departureDate;
    public String departurePort;
    public String directionType;
    public int infantCount;
    public boolean isAZ;
    public String lang;
    public String searchType;
}

