package com.medistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medistore.entity.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity,String> {

}
