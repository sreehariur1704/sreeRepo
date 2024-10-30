package com.medistore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medistore.dto.BookingDTO;
import com.medistore.service.IBookingService;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	@PostMapping(value = "/{bookingId}")
	public ResponseEntity<Boolean> rescheduleBooking(@PathVariable Integer bookingId,
			@RequestBody BookingDTO bookingDTO) throws Exception {
		Boolean flag = bookingService.rescheduleBooking(bookingId, bookingDTO);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{bookingId}")
	public ResponseEntity<Boolean> cancelAppointment(@PathVariable Integer bookingId) throws Exception {
		Boolean flag = bookingService.cancelAppointment(bookingId);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

}
