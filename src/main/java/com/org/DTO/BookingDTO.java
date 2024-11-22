package com.org.DTO;

import com.org.model.Flight;
import com.org.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int bookingId;
   private LocalDateTime bookingDate;
   private String status;
   private UserDTO user;
   private FlightDTO flight;

}
