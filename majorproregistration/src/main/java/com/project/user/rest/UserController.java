package com.project.user.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.bind.LoginForm;
import com.project.user.bind.RegistrationBind;
import com.project.user.bind.UserUnlock;
import com.project.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	public String checkEmail(@PathVariable String email) {
		String status = service.checkmail(email);
		return status;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<String> userRegistration(@RequestBody RegistrationBind bind){
		
		String status = service.userRegistration(bind);
		
		 return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
	
	@GetMapping("/getcountries")
	public Map<Integer,String> getcountries() {
		return service.getAllCountries();
	}
	
	@GetMapping("/country/{countryId}")
	public Map<Integer,String> getstates(@PathVariable Integer countryId) {
		return service.getState(countryId);
	}
	
	@GetMapping("/state/{stateId}")
	public Map<Integer,String> getcities(@PathVariable Integer stateId) {
		return service.getCities(stateId);
	}
	
	@PostMapping("/unlock")
	public String unlcok(@RequestBody UserUnlock userunlock) {
		String status = service.unlcok(userunlock);
		return status;
	}
	
	@GetMapping("/login")
	public String login(@RequestBody LoginForm login) {
		String status = service.login(login);
		return status;
	}

}
