package com.project.user.service;

import java.util.List;
import java.util.Map;

import com.project.user.bind.LoginForm;
import com.project.user.bind.RegistrationBind;
import com.project.user.bind.UserUnlock;
import com.project.user.entity.CountryEntity;

public interface UserService {
	
	public String checkmail(String email);

	public String userRegistration(RegistrationBind bind);
	
	public Map<Integer,String> getAllCountries();
	
	public Map<Integer,String> getState(Integer countryId);
	
	public Map<Integer,String> getCities(Integer stateId);
	
	public String unlcok(UserUnlock userunlock);
	
	public String login(LoginForm login);
	
	
}
