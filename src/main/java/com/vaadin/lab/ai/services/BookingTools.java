package com.vaadin.lab.ai.services;

import com.vaadin.lab.ai.data.BookingDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

@Service
public class BookingTools {

	private static final Logger logger = LoggerFactory.getLogger(BookingTools.class);

	private final FlightBookingService flightBookingService;

	@Autowired
	public BookingTools(FlightBookingService flightBookingService) {
		this.flightBookingService = flightBookingService;
	}

	@Tool(description = "Get booking details")
	public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
		try {
			return flightBookingService.getBookingDetails(bookingNumber, firstName, lastName);
		} catch (Exception e) {
			logger.warn("Booking details: {}", NestedExceptionUtils.getMostSpecificCause(e).getMessage());
			return new BookingDetails(bookingNumber, firstName, lastName, null, null,
					null, null, null, null);
		}
	}

	@Tool(description = "Change booking dates")
	public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from,
			String to) {
		flightBookingService.changeBooking(bookingNumber, firstName, lastName, newDate, from, to);
	};

	@Tool(description = "Cancel booking")
	public void cancelBooking(String bookingNumber, String firstName, String lastName) {
		flightBookingService.cancelBooking(bookingNumber, firstName, lastName);
	}

}
