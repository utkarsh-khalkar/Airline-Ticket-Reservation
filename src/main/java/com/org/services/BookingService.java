package com.org.services;

import com.org.Exceptions.DuplicateBookigException;
import com.org.model.Booking;
import com.org.model.Flight;
import com.org.model.User;
import com.org.repo.BookingRepo;
import com.org.repo.FlightRepo;
//import org.mule.runtime.core.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.org.Exceptions.ResourceNotFoundException;
import com.org.Exceptions.DuplicateBookigException;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserServices userService;

    /*
     * Retrieves all bookings
     */

    public List<Booking> getAllBookings(){
        return bookingRepo.findAll();
    }

    /*
     * Book a flight
     *
     */
    public Booking bookFlight(int flightId,int  userID) throws DuplicateBookigException {

        Flight flight1= flightRepo.findById(flightId).orElseThrow(()->new ResourceNotFoundException("Flight not found: "));
        User user=userService.getUserById(userID).orElseThrow(()->new ResourceNotFoundException("User not found:"));
        if(bookingRepo.existsByFlightAndUser(flight1,user)){
            throw new DuplicateBookigException("Booking is already made for this flight and user.");
        }
        Booking booking=new Booking();
        booking.setFlight(flight1);
        booking.setUser(user);
        bookingRepo.save(booking);
        return booking;

    }
}
