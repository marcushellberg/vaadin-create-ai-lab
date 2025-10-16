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
import org.springframework.ai.tool.annotation.ToolParam;
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

	@Tool(description = "Provides flight booking details")
	public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {

		logger.info("Tool: Provides flight booking details for bookingNumber: {}, firstName: {}, lastName: {}", bookingNumber,
				firstName, lastName);

		return this.flightBookingService.getBookingDetails(bookingNumber, firstName, lastName);
	}

	@Tool(description = "Use to change the date of flight booking")
	public void changeBooking(
		@ToolParam(description = "The booking number") String bookingNumber,
		@ToolParam(description = "The customer's first name") String firstName,
		@ToolParam(description = "The customer's last name") String lastName,
		@ToolParam(description = "The new date for the flight") String newDate,
		@ToolParam(description = "The departure location") String from,
		@ToolParam(description = "The destination location") String to) {

		logger.info(
				"Tool: Change flight booking for bookingNumber: {}, firstName: {}, lastName: {}, newDate: {}, from: {}, to: {}",
				bookingNumber, firstName, lastName, newDate, from, to);

		this.flightBookingService.changeBooking(bookingNumber, firstName, lastName, newDate, from, to);
	};

	@Tool(description = "Use to cancel an existing booking")
	public void cancelBooking(
		@ToolParam(description = "The booking number") String bookingNumber,
		@ToolParam(description = "The customer's first name") String firstName,
		@ToolParam(description = "The customer's last name") String lastName) {

		logger.info("Tool: Cancel flight booking for bookingNumber: {}, firstName: {}, lastName: {}", bookingNumber, firstName,
				lastName);

		this.flightBookingService.cancelBooking(bookingNumber, firstName, lastName);
	}

}
