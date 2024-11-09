package com.medistore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.medistore.dto.CoachDTO;
import com.medistore.entity.CoachEntity;

@Mapper
public interface MapStrutModelMapper {
	MapStrutModelMapper INSTANCE = Mappers.getMapper(MapStrutModelMapper.class);
	
	@Mapping(target="speciality", ignore = true)
	CoachDTO coachEntityToDto(CoachEntity coach);
}
