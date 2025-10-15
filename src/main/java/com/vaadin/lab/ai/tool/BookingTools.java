/*
 * Copyright 2025-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vaadin.lab.ai.tool;

import com.vaadin.lab.model.BookingDetails;
import com.vaadin.lab.services.FlightBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
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

		logger.info("Getting booking details for bookingNumber: {}, firstName: {}, lastName: {}", bookingNumber,
				firstName, lastName);

		return this.flightBookingService.getBookingDetails(bookingNumber, firstName, lastName);
	}

	@Tool(description = "Change booking dates")
	public void changeBooking(String bookingNumber, String firstName, String lastName, String newDate, String from,
			String to) {

		logger.info(
				"Changing booking for bookingNumber: {}, firstName: {}, lastName: {}, newDate: {}, from: {}, to: {}",
				bookingNumber, firstName, lastName, newDate, from, to);

		this.flightBookingService.changeBooking(bookingNumber, firstName, lastName, newDate, from, to);
	};

	@Tool(description = "Cancel booking")
	public void cancelBooking(String bookingNumber, String firstName, String lastName) {

		logger.info("Cancelling booking for bookingNumber: {}, firstName: {}, lastName: {}", bookingNumber, firstName,
				lastName);

		this.flightBookingService.cancelBooking(bookingNumber, firstName, lastName);
	}

}
