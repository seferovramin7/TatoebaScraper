package com.example.tatoebascraper.dto.flight.response;

import lombok.Builder;

@Builder
@lombok.Data
public class FlightResponse {
    public int status;
    public Message message;
    public Data data;

    public FlightResponse(int status, Message message, Data data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public FlightResponse() {
    }
}
