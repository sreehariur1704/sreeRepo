package com.medistore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.CoachDTO;
import com.medistore.service.ICoachService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/coaches")
public class CoachController {

	@Autowired
	private ICoachService coachService;

	@GetMapping(value = "/{coachId}")
	public ResponseEntity<CoachDTO> getCoachById(@PathVariable String coachId) {
		CoachDTO coachDTO = coachService.getCoachById(coachId);
		return new ResponseEntity<CoachDTO>(coachDTO, HttpStatus.OK);
	}
	@GetMapping(value = "/all")
	public ResponseEntity<List<CoachDTO>> getAllCoach() {
		List<CoachDTO> coachDTOs = coachService.getAllCoach();
		return new ResponseEntity<List<CoachDTO>>(coachDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/booking/{coachId}")
	public ResponseEntity<List<BookingDTO>> getAllBookingsForCoachId(@PathVariable String coachId) {
		List<BookingDTO> bookingDTOs = coachService.getAllBookingsForCoachId(coachId);
		return new ResponseEntity<List<BookingDTO>>(bookingDTOs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addCoach(@RequestBody @Valid CoachDTO coachDTO) {
		String coachId = coachService.addCoach(coachDTO);
		return new ResponseEntity<String>(coachId, HttpStatus.CREATED);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Boolean> loginCoaches(@RequestBody @Valid CoachDTO coachDTO) throws Exception {
		Boolean flag = coachService.loginCoaches(coachDTO);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

}
