package com.medistore.service;

import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;

@Service
public interface IBookingService {

	Boolean rescheduleBooking(Integer bookingId, BookingDTO bookingDTO) throws Exception;

	Boolean cancelAppointment(Integer bookingId) throws Exception;

}
