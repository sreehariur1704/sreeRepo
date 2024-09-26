package com.medistore.dto;

import java.time.LocalDate;

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
public class CoachDTO {
	
	private String coachId;
	private String name;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private Long mobileNumber;
	private String speciality;

}
