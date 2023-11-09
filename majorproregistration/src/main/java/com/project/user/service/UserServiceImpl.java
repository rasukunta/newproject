package com.project.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.bind.LoginForm;
import com.project.user.bind.RegistrationBind;
import com.project.user.bind.UserUnlock;
import com.project.user.entity.CityEntity;
import com.project.user.entity.CountryEntity;
import com.project.user.entity.RegistrationEntity;
import com.project.user.entity.StateEntity;
import com.project.user.repo.CityRepo;
import com.project.user.repo.CountryRepo;
import com.project.user.repo.RegistrationRepo;
import com.project.user.repo.StateRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RegistrationRepo repo;
	
	@Autowired
	private CountryRepo crepo;
	
	@Autowired
	private StateRepo srepo;
	
	@Autowired
	private CityRepo cityrepo;
	
	@Override
	public String checkmail(String email) {
		RegistrationEntity registrationEntity = repo.findByEmail(email);
		if(registrationEntity == null) {
			return "unique";
		}
		return "Duplicate";
	}

	@Override
	public String userRegistration(RegistrationBind bind) {
		RegistrationEntity entity =  new RegistrationEntity();
		
		BeanUtils.copyProperties(bind, entity);
		
		entity.setUserpwd(genPassword());
		
		entity.setAccStatus("Locked");
		
		if(entity.getUserId() == null) {
		repo.save(entity);
	    
	    
	    
	    return "Recorded Successfully";
		}
		return "Not Recorded";
	}
	
	public String genPassword() {
		
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		StringBuilder sb = new StringBuilder();
		
		Random r = new Random();
		
		for(int i=0;i<6;i++) {
			int index = r.nextInt(text.length());
			sb.append(text.charAt(index));
		}
		
		return sb.toString();
	}

	@Override
	public Map<Integer, String> getAllCountries() {
		List<CountryEntity> countries = crepo.findAll();
		Map<Integer,String> mapcou = new HashMap<>();
		
		for(CountryEntity country:countries) {
			mapcou.put(country.getCountryId(), country.getCountryName());
		}
		return mapcou;
	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {
		Map<Integer,String> mapsta = new HashMap<>();
		
		if(countryId != null) {
		List<StateEntity> states = srepo.findByCountryId(countryId);
		
		for(StateEntity state:states) {
			mapsta.put(state.getCountryId(), state.getStateName());
		}
		
		}
		return mapsta;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer,String> mapcity = new HashMap<>();
		
		if(stateId != null) {
			List<CityEntity> cities = cityrepo.findByStateId(stateId);
			
			
			for(CityEntity city:cities) {
				mapcity.put(city.getCityId(), city.getCityName());
			}
			
			}
		return mapcity;
	}

	@Override
	public String unlcok(UserUnlock userunlock) {
		String email = userunlock.getEmail();
		
		RegistrationEntity entity = repo.findByEmail(email);
		if(entity !=null && entity.getUserpwd().equals(userunlock.getTempPwd())){
			
			entity.setUserpwd(userunlock.getNewPwd());
			entity.setAccStatus("unlocked");
			repo.save(entity);
			
			return "Account unlocked";
		}
		return "Invalid Details";
	}

	@Override
	public String login(LoginForm login) {
	     RegistrationEntity entity = repo.findByEmailAndUserpwd(login.getEmail() ,login.getUserpwd());
	    	     
	    	     if(entity == null) {
	    	    	 return "Invalid Details";
	    	     }
	    	     if(entity.getAccStatus().equals("Locked")) {
	    	    	 return "Account Locked";
	    	     }
		return "Successfully login";
	}













}
