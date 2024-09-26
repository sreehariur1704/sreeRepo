package com.medistore.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "User")
public class UserEntity {
	@Id
	@Column(name = "user_id")
	private String userId;
	private String name;
	private String gender;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	private String password;
	@Column(name = "mobile_number")
	private String mobileNumber;
	private String email;
	private Integer pincode;
	private String city;
	private String state;
	private String country;
}
