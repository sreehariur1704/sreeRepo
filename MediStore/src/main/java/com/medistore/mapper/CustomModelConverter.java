package com.medistore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.CoachDTO;
import com.medistore.dto.UserDTO;
import com.medistore.entity.BookingEntity;
import com.medistore.entity.CoachEntity;
import com.medistore.entity.UserEntity;

@Component
public class CustomModelConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO userEntityToDto(UserEntity user) {
		
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setState(user.getState());
		dto.setPincode(user.getPincode());
		dto.setPassword(user.getPassword());
		dto.setName(user.getName());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setGender(user.getGender());
		dto.setEmail(user.getEmail());
		dto.setDateOfBirth(user.getDateOfBirth());
		dto.setCountry(user.getCountry());
		dto.setCity(user.getCity());
		
		return dto;
	}
	public List<UserDTO> userEntityToDto(List<UserEntity> user){
		return user.stream().map(x->userEntityToDto(x)).collect(Collectors.toList());
	}
	public UserEntity userDtoToEntity(UserDTO dto) {
		
		UserEntity user = new UserEntity();
//		user.setUserId(dto.getUserId());
//		user.setState(dto.getState());
//		user.setPincode(dto.getPincode());
//		user.setPassword(dto.getPassword());
//		user.setName(dto.getName());
//		user.setMobileNumber(dto.getMobileNumber());
//		user.setGender(dto.getGender());
//		user.setEmail(dto.getEmail());
//		user.setDateOfBirth(dto.getDateOfBirth());
//		user.setCountry(dto.getCountry());
//		user.setCity(dto.getCity());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		user = modelMapper.map(dto, UserEntity.class);
		
		return user;
	}
	public List<UserEntity> userDtoToEntity(List<UserDTO> dto){
		return dto.stream().map(x->userDtoToEntity(x)).collect(Collectors.toList());
	}
	
	public CoachDTO coachEntityToDto(CoachEntity coach) {
		
		CoachDTO dto = new CoachDTO();
		dto.setCoachId(coach.getCoachId());
		dto.setDateOfBirth(coach.getDateOfBirth());
		dto.setGender(coach.getGender());
		dto.setMobileNumber(coach.getMobileNumber());
		dto.setName(coach.getName());
		dto.setPassword(coach.getPassword());
		dto.setSpeciality(coach.getSpeciality());
		
		return dto;
	}
	public List<CoachDTO> coachEntityToDto(List<CoachEntity> coach){
		return coach.stream().map(x->coachEntityToDto(x)).collect(Collectors.toList());
	}
	public CoachEntity coachDtoToEntity(CoachDTO dto) {
		
		CoachEntity coach = new CoachEntity();
		coach.setCoachId(dto.getCoachId());
		coach.setDateOfBirth(dto.getDateOfBirth());
		coach.setGender(dto.getGender());
		coach.setMobileNumber(dto.getMobileNumber());
		coach.setName(dto.getName());
		coach.setPassword(dto.getPassword());
		coach.setSpeciality(dto.getSpeciality());
		
		return coach;
	}
	public List<CoachEntity> coachDtoToEntity(List<CoachDTO> dto){
		return dto.stream().map(x->coachDtoToEntity(x)).collect(Collectors.toList());
	}
	public BookingDTO bookingEntityToDto(BookingEntity booking) {
		
		BookingDTO dto = new BookingDTO();
		dto.setAppointmentDate(booking.getAppointmentDate());
		dto.setBookingId(booking.getBookingId());
		//dto.setCoachId(booking.getCoachId());
		dto.setSlot(booking.getSlot());
		//dto.setUserId(booking.getUserId());
		
		return dto;
	}
	public List<BookingDTO> bookingEntityToDto(List<BookingEntity> coach){
		return coach.stream().map(x->bookingEntityToDto(x)).collect(Collectors.toList());
	}
	public BookingEntity bookingDtoToEntity(BookingDTO dto) {
		
		BookingEntity booking = new BookingEntity();
		booking.setAppointmentDate(dto.getAppointmentDate());
		booking.setBookingId(dto.getBookingId());
		booking.setSlot(dto.getSlot());
		
		return booking;
	}
	public List<BookingEntity> bookingDtoToEntity(List<BookingDTO> dto){
		return dto.stream().map(x->bookingDtoToEntity(x)).collect(Collectors.toList());
	}
	

}
