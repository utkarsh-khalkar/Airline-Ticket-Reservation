package com.org.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private int flightId;
    private String flightNumber;
    private String deparatureLocation;
    private String destinationLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
