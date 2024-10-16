package com.nt.user.service;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nt.common.dao.CommonQueriesDao;
import com.nt.entity.UserRegistration;
import com.nt.user.dto.UserRegistrationDto;

@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationServiceTest {

	@Autowired
	UserRegistrationService userRegistrationService;
	
	
	UserRegistrationDto userRegistrationDto;
	
	@Autowired
	CommonQueriesDao commonQueriesDao;
	
	@Before
	public void setUp() throws Exception {
		userRegistrationDto=new UserRegistrationDto();
		userRegistrationDto.setFirstName("Sitha");
		userRegistrationDto.setLastName("Devi");
		userRegistrationDto.setDob(new Date());
		userRegistrationDto.setEmail("sitha@gmail.com");
		userRegistrationDto.setLangId(new String[]{"11","13"});
		userRegistrationDto.setGender("F");
		userRegistrationDto.setCountryCode("AU");
		userRegistrationDto.setStateCode("MH");
		userRegistrationDto.setUsername(userRegistrationDto.getFirstName()+""+userRegistrationDto.getLastName());
		userRegistrationDto.setPassword("naga138");
	}

	@Test
	@Ignore
	public void testCreateUser() {
		UserRegistration userRegistration=new UserRegistration();
		System.out.println(userRegistrationDto);
		userRegistration=userRegistrationService.createUser(userRegistrationDto);
		System.out.println(" New User is created with id "+userRegistration.getId());
	}
	
	@Test
	@Ignore
	public void testRetriveAllUsers() {
		System.out.println(userRegistrationService.getAllUsers().size());
		userRegistrationService.getAllUsers().forEach(user->{
			System.out.println(user.toString());
		});
	}

	
	@Test
	@Transactional
	@Ignore
	public void testUpdateUser() {
		List<UserRegistrationDto> list =userRegistrationService.getAllUsers();
		userRegistrationDto.setUserid(list.get(1).getUserid());
		userRegistrationDto.setFirstName("Mahesh");
		userRegistrationDto.setLastName("Vardan");
		userRegistrationDto.setUserCredId(list.get(1).getUserCredId());
		userRegistrationDto.setLangId(list.get(1).getLangId());
		userRegistrationDto.setUsername(userRegistrationDto.getFirstName()+""+userRegistrationDto.getLastName());
		userRegistrationService.updateUser(userRegistrationDto);
	}
	
	
	@Test
	@Ignore
	public void testdeleteUser() {
		System.out.println(userRegistrationService.deleteUserById(18));
	}

}
