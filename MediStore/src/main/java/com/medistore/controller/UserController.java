package com.medistore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medistore.dto.UserDTO;
import com.medistore.service.IUserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired 
	private IUserService userService;
	
	
	@GetMapping(value= "/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
		UserDTO userDto = userService.getUserById(userId);
		return new ResponseEntity<UserDTO>(userDto,HttpStatus.OK);
	}
}
