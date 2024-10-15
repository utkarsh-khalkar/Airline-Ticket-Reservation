package com.org.controller;

import com.org.Exceptions.DuplicateBookigException;
import com.org.model.Booking;
import com.org.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /*
     * Retrieves all bookings
     */
    //http://localhost:8080/api/v1/bookings/bookings
    @GetMapping("/bookings")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    // http://localhost:8080/api/v1/bookings/bookFlight/{flightId}/{userID}
    @PostMapping("/bookFlight/{flightId}/{userID}")
    public ResponseEntity<Booking> bookFlight(@PathVariable int flightId, @PathVariable int userID) throws DuplicateBookigException {
        Booking booking = bookingService.bookFlight(flightId, userID);
        return ResponseEntity.ok(booking);
    }

}
