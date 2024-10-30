package com.medistore.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.UserDTO;
import com.medistore.dto.UserPrincipal;
import com.medistore.entity.BookingEntity;
import com.medistore.entity.CoachEntity;
import com.medistore.entity.UserEntity;
import com.medistore.mapper.CustomModelConverter;
import com.medistore.repository.BookingRepository;
import com.medistore.repository.CoachRepository;
import com.medistore.repository.UserRepository;

@Service
public class UserService implements IUserService,UserDetailsService {
	
	//org.slf4j.Logger
	//private static final Logger LOGGER =LoggerFactory.getLogger(UserService.class);
	//org.apache.logging.log4j
	private static final Logger LOGGER =LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BookingRepository bookingrepo;
	@Autowired
	private CoachRepository coachrepo;
	@Autowired
	private Environment env;
	@Autowired
	 @Lazy
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

	@Autowired
	private CustomModelConverter converter;
	

	@Override
	public UserDTO getUserById(String userId) {
		Optional<UserEntity> opt = userrepo.findById(userId);
		if (opt.isEmpty()) {
			LOGGER.trace("no user found from trace");
			LOGGER.debug("no user found from debug");
			LOGGER.info("no user found from info");
			LOGGER.warn("no user found from warn");
			LOGGER.error("no user found from error");
			LOGGER.fatal("no user found from fatal");
			return null;
		} else {
			return converter.userEntityToDto(opt.get());
		}
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		UserEntity userEntity = userrepo.save(converter.userDtoToEntity(userDTO));
		System.out.println( "User successfully registered with user id: " + userDTO.getUserId());
		return converter.userEntityToDto(userEntity);
	}

	@Override
	public Boolean bookAppointment(String userId, String coachId, BookingDTO bookingDto) throws Exception {
		Boolean flag = true;
		List<BookingEntity> bookings = bookingrepo.findByUser_userIdAndCoach_coachId(userId, coachId);

		for (BookingEntity booking : bookings) {
			if (booking.getAppointmentDate().equals(bookingDto.getAppointmentDate())
					&& booking.getSlot().equals(bookingDto.getSlot())) {
				throw new Exception(env.getProperty("BOOKING ALREADY_EXISTS"));
			}
		}
		Optional<UserEntity> user = userrepo.findById(userId);

		if (user.isEmpty()) {
			throw new Exception(env.getProperty("USER_NOT FOUND"));
		}

		Optional<CoachEntity> coach = coachrepo.findById(coachId);
		if (coach.isEmpty()) {
			throw new Exception(env.getProperty("COACH_NOT_FOUND"));
		}
		BookingEntity coachBooking = bookingrepo.findByCoach_coachIdAndAppointmentDateAndSlot(coachId,
				bookingDto.getAppointmentDate(), bookingDto.getSlot());
		if (coachBooking != null) {
			throw new Exception(env.getProperty("COACH_SLOT_OCCUPIED"));
		}
		BookingEntity bookEntity = converter.bookingDtoToEntity(bookingDto);

		bookingrepo.save(bookEntity);
		flag = true;
		return flag;
	}

	@Override
	public String loginUser(UserDTO userDTO) {
		Authentication authentication =
				authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserId(), userDTO.getPassword()));
		//UserDTO dto = getUserById(userDTO.getUserId());
		JWTService jwtService = new JWTService();
		if (authentication.isAuthenticated()) {
			
			return jwtService.generateToken(userDTO.getUserId());
		}else {
			return "failure";
		}
	}

	@Override
	public List<BookingDTO> getBookingsForUserId(String userId) {
		List<BookingEntity> bookingEntity = bookingrepo.findByuser_UserId(userId);

		return converter.bookingEntityToDto(bookingEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userrepo.getUserByUserId(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(converter.userEntityToDto(userEntity));
	}

}
