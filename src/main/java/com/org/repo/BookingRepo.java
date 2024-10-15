package com.org.repo;

import com.org.model.Booking;
import com.org.model.Flight;
import com.org.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    boolean existsByFlightAndUser(Flight flight1, User user);
}
