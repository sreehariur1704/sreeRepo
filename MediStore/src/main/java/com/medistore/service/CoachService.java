package com.medistore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.medistore.dto.BookingDTO;
import com.medistore.dto.CoachDTO;
import com.medistore.entity.BookingEntity;
import com.medistore.entity.CoachEntity;
import com.medistore.mapper.CustomModelConverter;
import com.medistore.repository.BookingRepository;
import com.medistore.repository.CoachRepository;

import jakarta.validation.Valid;

@Service
public class CoachService implements ICoachService {
	
	@Autowired
	private CoachRepository coachrepo;
	
	@Autowired
	private BookingRepository bookingrepo;
	
	@Autowired
	private CustomModelConverter converter;
	
	@Autowired
	private Environment env;
	
	@Override
	public List<CoachDTO> getAllCoach() {
		List<CoachEntity> coachEntity = coachrepo.findAll();
		return converter.coachEntityToDto(coachEntity);
	}

	@Override
	public List<BookingDTO> getAllBookingsForCoachId(String coachId) {
		List<BookingEntity> bookingEntity = bookingrepo.findByCoach_CoachId(coachId);
		return converter.bookingEntityToDto(bookingEntity);
	}

	@Override
	public CoachDTO getCoachById(String coachId) {
		Optional<CoachEntity> coachEntity = coachrepo.findById(coachId);
		if(coachEntity.isEmpty()) {
			return null;
		}else {
			return converter.coachEntityToDto(coachEntity.get());
		}
	}

	@Override
	public String addCoach( CoachDTO coachDTO) {
		coachrepo.save(converter.coachDtoToEntity(coachDTO));
		return "coach with id"+coachDTO.getCoachId()+"created successfully";
	}

	@Override
	public Boolean loginCoaches( CoachDTO coachDTO) throws Exception {
		Boolean flag = false;
		CoachDTO dto = getCoachById(coachDTO.getCoachId());
		if(dto==null) {
			throw new Exception(env.getProperty("COACH_NOT_FOUND")+coachDTO.getCoachId());
		}
		if(dto.getPassword().equalsIgnoreCase(coachDTO.getPassword())) {
			flag = true;
		}
		return flag;
	}

}
