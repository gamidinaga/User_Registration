package com.nt.user.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.user.service.UserRegistrationService;

@Component
public class UserStatusScheduler {
	@Autowired
	private UserRegistrationService service;
	
	public String getUserStatus() {
		
		
		
		return null;
	}

}
