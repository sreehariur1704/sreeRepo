package com.medistore.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "Coach")
public class CoachEntity {
	@Id
	@Column(name = "coach_id")
	private String coachId;
	private String name;
	private String gender;
	
//	@Column(nullable = false)
//	@Enumerated(value=EnumType.STRING)
//	private Gender gender;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	private String password;
	
	@Column(name = "mobile_number")
	private Long mobileNumber;
	
	private String speciality;
}
