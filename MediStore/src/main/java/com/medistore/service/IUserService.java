package com.medistore.service;

import org.springframework.stereotype.Service;

import com.medistore.dto.UserDTO;

@Service
public interface IUserService {

	UserDTO getUserById(String userId);

}
