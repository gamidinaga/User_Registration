package com.nt.user.dao;

import java.util.List;

import com.nt.entity.UserCredentials;
import com.nt.entity.UserRegistration;

public interface UserRegistrationDAO {

	public UserRegistration createUser(UserRegistration userRegistration);

	public List<UserRegistration> retrieveAllUsers();

	public int deleteUser(int userId);

	public void updateUser(UserRegistration userRegistration);
	
	public UserRegistration loadUserById(int userId);
	
	public UserCredentials updateUserStatus(UserCredentials userCredentials);
}
