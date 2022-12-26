package com.example.tatoebascraper.dto.flight;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Destinations {
    public String value;
    public String name;
    public String search;
}
