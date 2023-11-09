package com.project.user.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.user.entity.CityEntity;

public interface CityRepo  extends JpaRepository<CityEntity, Serializable>{
	
	public List<CityEntity> findByStateId(Integer stateId);

}
