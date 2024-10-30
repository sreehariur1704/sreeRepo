package com.medistore.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medistore.entity.BookingEntity;


public interface BookingRepository extends JpaRepository<BookingEntity,Integer>{

	public List<BookingEntity> findByuser_UserId(String userId);

	public BookingEntity findBycoach_coachIdAndAppointmentDateAndSlot(String coachId, LocalDate appointmentDate, String slot);

	public List<BookingEntity> findByUser_userIdAndCoach_coachId(String userId, String coachId);

	public BookingEntity findByCoach_coachIdAndAppointmentDateAndSlot(String coachId, LocalDate appointmentDate,
			String slot);

	public List<BookingEntity> findByCoach_CoachId(String coachId);

}
