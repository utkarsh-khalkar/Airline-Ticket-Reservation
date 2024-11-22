package com.org.services;

import com.org.DTO.BookingDTO;
import com.org.DTO.FlightDTO;
import com.org.DTO.UserDTO;
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
import java.util.stream.Collectors;

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

    public List<BookingDTO> getAllBookings(){
       List<Booking> bookings=bookingRepo.findAll();
       return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BookingDTO convertToDTO(Booking booking){

        // Map Flight Details
        FlightDTO flightDTO=new FlightDTO();
        flightDTO.setFlightId(booking.getFlight().getFlightId());
        flightDTO.setFlightNumber(booking.getFlight().getFlightNumber());
        flightDTO.setDeparatureLocation(booking.getFlight().getDeparatureLocation());
        flightDTO.setDestinationLocation(booking.getFlight().getDestinationLocation());
        flightDTO.setDepartureTime(booking.getFlight().getDepartureTime());
        flightDTO.setArrivalTime(booking.getFlight().getArrivalTime());

        // Map user Details
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(booking.getUser().getUserId());
        userDTO.setUsername(booking.getUser().getUsername());
        userDTO.setEmail(booking.getUser().getEmail());

        // Map booking Details
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setBookingDate(booking.getBooking_date());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setFlight(flightDTO);
        bookingDTO.setUser(userDTO);
        return bookingDTO;
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
