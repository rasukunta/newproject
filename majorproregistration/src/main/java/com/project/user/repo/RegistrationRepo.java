package com.project.user.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.user.entity.RegistrationEntity;

public interface RegistrationRepo extends JpaRepository<RegistrationEntity, Serializable> {

	public RegistrationEntity findByEmail(String email);
	
	public RegistrationEntity findByEmailAndUserpwd(String email,String userpwd);
}
