package com.medistore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;
import com.medistore.entity.BookingEntity;
import com.medistore.repository.BookingRepository;

@Service
public class BookingService implements IBookingService {
	
	@Autowired
	private BookingRepository bookingrepo;
	@Autowired
	private Environment env;

	@Override
	public Boolean rescheduleBooking(Integer bookingId, BookingDTO bookingDTO) throws Exception {
		Boolean flag = false;
		Optional<BookingEntity> bookingEntity = bookingrepo.findById(bookingId);
		if(bookingEntity.isEmpty()) {
			throw new Exception(env.getProperty("BOOKING_NOT_FOUND"));
		}else {
			bookingrepo.save(bookingEntity.get());
			flag = true;
		}
		BookingEntity coachBooking = bookingrepo.findBycoach_coachIdAndAppointmentDateAndSlot(bookingEntity.get().getCoach().getCoachId(),
				bookingDTO.getAppointmentDate(),bookingDTO.getSlot());
		if(coachBooking != null) {
			throw new Exception(env.getProperty("COACH_SLOT_OCCUPIED"));
		}
		return flag;
	}

	@Override
	public Boolean cancelAppointment(Integer bookingId) throws Exception {
		Optional<BookingEntity> booking = bookingrepo.findById(bookingId);
		if(booking != null) {
			throw new Exception(env.getProperty("BOOKING_NOT_FOUND"));
		}
		bookingrepo.delete(booking.get());
		return true;
	}
	
//	@Override
//	public List<BookingDTO> getBookingsForUserId(String userId) {
//		Ḷist<BookingDTO> bookingEntity = userrepo.findByuser_UserId(userId);
//		return null;
//	}

}
