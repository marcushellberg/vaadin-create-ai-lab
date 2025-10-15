package com.vaadin.lab.ai.repository;

import com.vaadin.lab.ai.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingNumberIgnoreCaseAndCustomerFirstNameIgnoreCaseAndCustomerLastNameIgnoreCase(
            String bookingNumber, String firstName, String lastName);

    List<Booking> findAllByOrderByDateAsc();
}
