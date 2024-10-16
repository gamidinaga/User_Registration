package com.nt.user.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nt.common.dao.CommonQueriesDao;
import com.nt.entity.UserCredentials;
import com.nt.entity.UserLanguages;
import com.nt.entity.UserRegistration;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistratonDaoTest {
	@Autowired
	UserRegistrationDAO userRegistrationDAO;

	@Autowired
	CommonQueriesDao commonQueriesDao;

	private UserRegistration userRegistration;

	private UserCredentials userCredentials;

	private UserLanguages userLanguages;

	@Before
	public void setUpUser() {
		userRegistration = new UserRegistration();
		userCredentials = new UserCredentials();

		userRegistration.setFirstName("naga");
		userRegistration.setLastName("raja");
		userRegistration.setDob(new Date());
		userRegistration.setEmail("gamidi@gmail.com");
		userRegistration.setGender("M");
		userRegistration.setState(commonQueriesDao.findStateByCode("AP"));
		userRegistration.setCountry(commonQueriesDao.findCountryByCode("IN"));
		userCredentials.setUsername(userRegistration.getFirstName() + userRegistration.getLastName());
		userCredentials.setPassword("ramudu");
		userLanguages = new UserLanguages();

		userLanguages.setLangId(1);
		userLanguages.setLangDesc("Telugu");
		userLanguages.setUserReg(userRegistration);
		userRegistration.getUserlanguagesSet().add(userLanguages);

		userLanguages = new UserLanguages();
		userLanguages.setLangId(2);
		userLanguages.setLangDesc("Hindi");
		userLanguages.setUserReg(userRegistration);
		userRegistration.getUserlanguagesSet().add(userLanguages);

		userLanguages = new UserLanguages();
		userLanguages.setLangId(3);
		userLanguages.setLangDesc("English");
		userLanguages.setUserReg(userRegistration);
		userRegistration.getUserlanguagesSet().add(userLanguages);
		userCredentials.setUserRegistration(userRegistration);
		userRegistration.setUserCredentials(userCredentials);
	}

	@Test
@Ignore
	@Transactional(value = TxType.REQUIRES_NEW)
	public void testCreateUser() {
		userRegistrationDAO.createUser(this.userRegistration);
		List<UserRegistration> list = userRegistrationDAO.retrieveAllUsers();
		Assert.assertNotNull(list);
		System.out.println("User List Size in create " + list.size()); //
		System.out.println("User List Data is" + list.toString());
	}

	@Test
	@Ignore
	@Transactional(value = TxType.REQUIRES_NEW)
	public void testDeleteUser() {
		List<UserRegistration> list = userRegistrationDAO.retrieveAllUsers();
		Assert.assertNotNull(list);
		System.out.println("User List Size is " + list.size());
		if (!list.isEmpty()) {
			UserRegistration userToDelete = list.get(0);
			System.out.println("User is deleting ---->" + userToDelete.getId());
			userRegistrationDAO.deleteUser(userToDelete.getId());
		}

	}

	@Test
	@Ignore
	@Transactional(value = TxType.REQUIRES_NEW)
	public void testUpdateUser() {
		List<UserRegistration> list = userRegistrationDAO.retrieveAllUsers();
		UserRegistration userToUpdate = list.get(2);
		UserCredentials credentialToUpdate = userToUpdate.getUserCredentials();
		UserLanguages languagesToUpdate = userToUpdate.getUserlanguagesSet().get(0);
		// userToUpdate = userRegistrationDAO.loadUserById(22);
		// userLanguages
		userToUpdate.setFirstName("srinu");
		userToUpdate.setLastName("rao");
		credentialToUpdate.setUsername(userToUpdate.getFirstName() + userToUpdate.getLastName());
		credentialToUpdate.setPassword(userToUpdate.getUserCredentials().getPassword());
		userToUpdate.setUserCredentials(credentialToUpdate);
		languagesToUpdate.setLangDesc("TEST124");
		languagesToUpdate.setUserReg(userToUpdate);
		userToUpdate.getUserlanguagesSet().add(languagesToUpdate);
		userToUpdate.setUserlanguagesSet(userToUpdate.getUserlanguagesSet());
		userToUpdate.setCountry(commonQueriesDao.findCountryByCode("AU"));
		// System.out.println("User is Updated ---->" + userToUpdate);
		userRegistrationDAO.updateUser(userToUpdate);

		System.out.println("User is Updated ---->" + userToUpdate.getId());
	}
}
