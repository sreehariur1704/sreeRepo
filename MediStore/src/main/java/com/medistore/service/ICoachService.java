package com.medistore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.CoachDTO;

import jakarta.validation.Valid;

@Service
public interface ICoachService {

	List<CoachDTO> getAllCoach();

	List<BookingDTO> getAllBookingsForCoachId(String coachId);

	CoachDTO getCoachById(String coachId);

	String addCoach(@Valid CoachDTO coachDTO);

	Boolean loginCoaches(CoachDTO coachDTO) throws Exception;

}
