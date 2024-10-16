package com.nt.user.service;

import java.util.List;

import com.nt.common.utils.Country;
import com.nt.common.utils.State;
import com.nt.entity.UserCredentials;
import com.nt.entity.UserRegistration;
import com.nt.user.dto.UserRegistrationDto;

public interface UserRegistrationService {
	
	  List<Country> findAllCountryList();
	   List<State> findAllStateList();
	  UserRegistration createUser(UserRegistrationDto regDto);
	  UserRegistration updateUser(UserRegistrationDto regDto);
       List<UserRegistrationDto> getAllUsers();
  	  String deleteUserById(int userId); 
  	   UserRegistrationDto loadUserById(int userid);
  	   String updateUserStatus(int userId);
}
