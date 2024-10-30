package com.medistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medistore.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,String>{


	UserEntity getUserByUserId(String username);

}
