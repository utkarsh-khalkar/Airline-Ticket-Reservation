package com.org.controller;

import com.org.model.Flight;
import com.org.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flight")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // http://localhost:8080/api/v1/flight/flights
    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/addFlight")
    public ResponseEntity<String> addFlight(@RequestBody Flight flight) {
        flightService.getOrUpdateFlight(flight);
        return ResponseEntity.ok("Flight added successfully");
    }

    @DeleteMapping("/deleteFlight/{id}")
    public ResponseEntity<String> deleteFlightByID(@PathVariable int id) {
        flightService.deleteFlightByID(id);
        return ResponseEntity.ok("Flight deleted successfully");
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int id) {
        return ResponseEntity.ok(flightService.getFlightById(id).orElse(null));
    }


}
