package com.org.services;

import com.org.model.Flight;
import com.org.repo.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepo flightRepo;
    /*
     * Retrieves all flights
     */
    public List<Flight> getAllFlights(){
        return flightRepo.findAll();
    }
    /*
     * Saves a flight
     * or Update Flight
     */
    public void getOrUpdateFlight(Flight flight){
        flightRepo.save(flight);
    }
    /*
     * Retrieves a flight by their ID

     */
    public void deleteFlightByID(int id)
    {
        flightRepo.deleteById(id);
    }
    /*
     * Retrieves a flight by their ID
     */
    public Optional<Flight> getFlightById(int id)
    {
        return flightRepo.findById(id);
    }
}
