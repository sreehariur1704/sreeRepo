package com.medistore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.UserDTO;
import com.medistore.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired 
	private IUserService userService;
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	
	@GetMapping(value= "/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable String userId) {
		UserDTO userDto = userService.getUserById(userId);
		if(userDto==null) {
			String format = String.format("user with userid: %s not found", userId);
			return new ResponseEntity<>(format,HttpStatus.OK);
		}
		return new ResponseEntity<UserDTO>(userDto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/booking/{userId}")
	public ResponseEntity<List<BookingDTO>> getAllBookingsForUserId(@PathVariable String userId){
		List<BookingDTO> bookingDTOs = userService.getBookingsForUserId(userId);
		return new ResponseEntity<List<BookingDTO>>(bookingDTOs,HttpStatus.OK);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO,HttpServletRequest request){
		System.out.println(" session id: "+request.getSession().getId());
		UserDTO userDTO1 = userService.addUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO1,HttpStatus.CREATED);
	}
	@PostMapping(value="/login")
	public ResponseEntity<String> loginUser(@RequestBody @Valid UserDTO userDTO){
		String flag = userService.loginUser(userDTO);
		return new ResponseEntity<String>(flag,HttpStatus.OK);
	}
	@PostMapping(value="/{userId}/booking/{coachId}")
	public ResponseEntity<Boolean> bookAppointment(@PathVariable String userId,@PathVariable String coachId,
			@RequestBody BookingDTO bookingDto) throws Exception{
		Boolean flag = userService.bookAppointment(userId,coachId,bookingDto);
		return new ResponseEntity<Boolean>(flag,HttpStatus.OK);
	}
	
}
