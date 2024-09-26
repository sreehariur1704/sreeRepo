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
public class UserDTO {
	
	private String userId;
	private String name;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private String mobileNumber;
	private String email;
	private Integer pincode;
	private String city;
	private String state;
	private String country;

}
