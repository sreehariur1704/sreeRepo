package com.medistore.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BookingDTO {
	
	private Integer bookingId;
	private String userId;
	private String coachId;
	//@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private LocalDate appointmentDate;
	private String slot;
}
