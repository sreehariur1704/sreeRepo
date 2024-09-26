package com.medistore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medistore.dto.UserDTO;
import com.medistore.entity.UserEntity;
import com.medistore.repository.UserRepository;
@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public UserDTO getUserById(String userId) {
		Optional<UserEntity> opt = userrepo.findById(userId);
		if(opt.isEmpty()) {
			return null;
		}else {
			return null;
		}
	}

}
