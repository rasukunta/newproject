package com.project.user.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.user.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Serializable> {

}
