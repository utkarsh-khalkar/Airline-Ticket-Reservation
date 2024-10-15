package com.org.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id",nullable = false)
    private int flightId;

    @Column(name = "flight_number",nullable = false)
    private String flightNumber;

    @Column(name = "deparature_location",nullable = false)
    private String deparatureLocation;

    @Column(name = "destination_location",nullable = false)
    private String destinationLocation;

    @Column(name = "departure_time",nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time",nullable = false)
    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Breaks the circular reference
    private List<Booking> bookings = new ArrayList<>();


}
