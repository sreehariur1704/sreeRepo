package com.medistore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.UserDTO;

@Service
public interface IUserService {

	UserDTO getUserById(String userId);

	List<BookingDTO> getBookingsForUserId(String userId);

	UserDTO addUser(UserDTO userDTO);

	String loginUser(UserDTO userDTO);

	Boolean bookAppointment(String userId, String coachId, BookingDTO bookingDto) throws Exception;

}
