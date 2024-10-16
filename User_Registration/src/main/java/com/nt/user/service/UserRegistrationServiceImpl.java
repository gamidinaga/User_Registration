package com.nt.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.common.dao.CommonQueriesDao;
import com.nt.common.utils.Country;
import com.nt.common.utils.EmailUtility;
import com.nt.common.utils.State;
import com.nt.entity.UserCredentials;
import com.nt.entity.UserLanguages;
import com.nt.entity.UserRegistration;
import com.nt.entity.UserStatus;
import com.nt.user.dao.UserRegistrationDAO;
import com.nt.user.dto.UserRegistrationDto;

@Service(value = "regService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserRegistrationServiceImpl implements UserRegistrationService {
	private static Logger logger = Logger.getLogger(UserRegistrationServiceImpl.class);
	private static Map<Integer, String> languageMap = new HashMap<>();

	static {
		// Log4JConfiguration.log4jConfigurer();
		languageMap.put(11, "Telugu");
		languageMap.put(12, "English");
		languageMap.put(13, "Hindi");
	}
	@Autowired
	private UserRegistrationDAO userRegistrationDAO;

	@Autowired
	private CommonQueriesDao commonQueriesDao;

	@Override
	public List<Country> findAllCountryList() {
		return commonQueriesDao.listAllCountry();
	}

	@Override
	public List<State> findAllStateList() {
		return commonQueriesDao.listAllState();
	}

	@Override
	public UserRegistration createUser(UserRegistrationDto userRegistrationDto) {
		List<UserLanguages> userLanguagesList = new ArrayList<>();
		UserRegistration userRegistration = null;
		UserCredentials userCredentials = null;
		UserLanguages userLanguages = null;

		// copying the Dto to UserRegistration class
		userRegistration = new UserRegistration();
		userRegistration.setFirstName(userRegistrationDto.getFirstName());
		userRegistration.setLastName(userRegistrationDto.getLastName());
		userRegistration.setDob(userRegistrationDto.getDob());
		userRegistration.setEmail(userRegistrationDto.getEmail());
		userRegistration.setPhone(userRegistrationDto.getPhone());
		userRegistration.setAddress(userRegistrationDto.getAddress());
		userRegistration.setCountry(commonQueriesDao.findCountryByCode(userRegistrationDto.getCountryCode()));
		userRegistration.setState(commonQueriesDao.findStateByCode(userRegistrationDto.getStateCode()));
		userRegistration.setGender(userRegistrationDto.getGender());
		// copying the Dto to UserLanguages class
		for (String lanId : userRegistrationDto.getLangId()) {
			userLanguages = new UserLanguages();
			userLanguages.setLangId(Integer.parseInt(lanId));
			userLanguages.setLangDesc(languageMap.get(Integer.parseInt(lanId)));
			userLanguagesList.add(userLanguages);
			userLanguages.setUserReg(userRegistration);
			userRegistration.setUserlanguagesSet(userLanguagesList);
		}

		// copying the Dto to UserCredentials class
		userCredentials = new UserCredentials();
		userCredentials.setUsername(userRegistrationDto.getUsername());
		userCredentials.setUserStatus(UserStatus.CREATED);
		userCredentials.setAuthValue(userRegistration.getFirstName().concat(UUID.randomUUID().toString()));
		userCredentials.setPassword(userRegistrationDto.getPassword());
		userCredentials.setUserCreatedDate(new Date());
		userCredentials.setUserRegistration(userRegistration);
		userRegistration.setUserCredentials(userCredentials);

		// calling dao method to create user
		userRegistration=userRegistrationDAO.createUser(userRegistration);
		EmailUtility.sendEmailToUser(userRegistration);
		return userRegistration;
	}

	@Override
	public UserRegistration updateUser(UserRegistrationDto userRegistrationDto) {
		logger.info(":::::::::::::::::::::::::Start of updateUser::::::::::::::::::");
		List<UserLanguages> userLanguagesList = new ArrayList<>();
		UserRegistration userToUpdate = null;
		UserCredentials credentialToUpdate = null;
		UserLanguages languagesToUpdate = null;

		userToUpdate = userRegistrationDAO.loadUserById(userRegistrationDto.getUserid());

		userToUpdate.setFirstName(userRegistrationDto.getFirstName());
		userToUpdate.setLastName(userRegistrationDto.getLastName());
		userToUpdate.setDob(userRegistrationDto.getDob());
		userToUpdate.setEmail(userRegistrationDto.getEmail());
		userToUpdate.setPhone(userRegistrationDto.getPhone());
		userToUpdate.setAddress(userRegistrationDto.getAddress());
		userToUpdate.setCountry(commonQueriesDao.findCountryByCode(userRegistrationDto.getCountryCode()));
		userToUpdate.setState(commonQueriesDao.findStateByCode(userRegistrationDto.getStateCode()));
		userToUpdate.setGender(userRegistrationDto.getGender());

		credentialToUpdate = userToUpdate.getUserCredentials();
		credentialToUpdate.setUsername(userRegistrationDto.getUsername());
		credentialToUpdate.setPassword(userRegistrationDto.getPassword());
		credentialToUpdate.setUserUpdatedDate(new Date());
		userToUpdate.setUserCredentials(credentialToUpdate);

		for (String lanId : userRegistrationDto.getLangId()) {
			int i = 0;
			languagesToUpdate = userToUpdate.getUserlanguagesSet().get(i);
			languagesToUpdate.setLangId(Integer.parseInt(lanId));
			languagesToUpdate.setLangDesc(languageMap.get(Integer.parseInt(lanId)));
			userLanguagesList.add(languagesToUpdate);
			languagesToUpdate.setUserReg(userToUpdate);
			i++;
		}
		userToUpdate.setUserlanguagesSet(userLanguagesList);
		System.out.println("in service :updating");
		userRegistrationDAO.updateUser(userToUpdate);
		logger.info(":::::::::::::::::::::::::End of updateUser::::::::::::::::::");

		return userToUpdate;
	}

	@Override
	public List<UserRegistrationDto> getAllUsers() {
		List<UserRegistrationDto> usersListDto = new ArrayList<>();
		List<UserRegistration> usersList;
		usersList = userRegistrationDAO.retrieveAllUsers();
		usersList.forEach(userEntity -> {
			UserRegistrationDto userDto = new UserRegistrationDto();
			StringBuffer sb = new StringBuffer();
			userDto.setUserid(userEntity.getId());
			userDto.setFirstName(userEntity.getFirstName());
			userDto.setLastName(userEntity.getLastName());
			userDto.setDob(userEntity.getDob());
			userDto.setEmail(userEntity.getEmail());
			userDto.setPhone(userEntity.getPhone());
			userDto.setAddress(userEntity.getAddress());
			userDto.setCountryCode(userEntity.getCountry().getCountryCode());
			userDto.setStateCode(userEntity.getState().getStateCode());
			userDto.setGender(userEntity.getGender());
			List<UserLanguages> userLangs = new ArrayList<UserLanguages>();
			userLangs = userEntity.getUserlanguagesSet();
			userLangs.forEach(userLang -> {
				sb.append(userLang.getLangDesc()).append(",");
			});
			 //String langDesc = sb.substring(0, sb.length()-1);
			String langDesc=sb.toString().trim();
			 userDto.setLangDesc(langDesc);
			System.out.println(userEntity.getUserlanguagesSet().size());
			userDto.setUserCredId(userEntity.getUserCredentials().getId());
			userDto.setUsername(userEntity.getUserCredentials().getUsername());
			userDto.setPassword(userEntity.getUserCredentials().getPassword());
			// BeanUtils.copyProperties(user, userdto);
			usersListDto.add(userDto);
		});
		return usersListDto;
	}

	@Override
	public String deleteUserById(int userId) {
		int deletedUserId = userRegistrationDAO.deleteUser(userId);
		if (deletedUserId == 0) {
			return "Sorry " + userId + " is Not deleted duo to internal Problem";
		}
		return userId + " is Deleted Sucssesfully";
	}

	@Override
	public UserRegistrationDto loadUserById(int userId) {
		List<UserLanguages> userLangs = new ArrayList<UserLanguages>();
		List<String> userLangId = new ArrayList<>();
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		UserRegistration userRegistration = null;

		userRegistration = userRegistrationDAO.loadUserById(userId);
		userRegistrationDto.setUserid(userRegistration.getId());
		userRegistrationDto.setFirstName(userRegistration.getFirstName());
		userRegistrationDto.setLastName(userRegistration.getLastName());
		userRegistrationDto.setDob(userRegistration.getDob());
		userRegistrationDto.setEmail(userRegistration.getEmail());
		userRegistrationDto.setCountryCode(userRegistration.getCountry().getCountryCode());
		userRegistrationDto.setStateCode(userRegistration.getState().getStateCode());
		userLangs = userRegistration.getUserlanguagesSet();
		userLangs.forEach(userLang -> {
			userLangId.add(String.valueOf(userLang.getLangId()));
		});
		userRegistrationDto.setLangId(userLangId.toArray(new String[0]));
		userRegistrationDto.setGender(userRegistration.getGender());
		userRegistrationDto.setUserCredId(userRegistration.getUserCredentials().getId());
		userRegistrationDto.setPassword(userRegistration.getUserCredentials().getPassword());
		return userRegistrationDto;
	}

	@Override
	public String updateUserStatus(int userId) {
		UserRegistration userToUpdate=userRegistrationDAO.loadUserById(userId);
		UserCredentials credentialToUpdate= userToUpdate.getUserCredentials();
		credentialToUpdate.setAuthValue(credentialToUpdate.getAuthValue());
		credentialToUpdate.setUsername(credentialToUpdate.getUsername());
		credentialToUpdate.setPassword(credentialToUpdate.getPassword());
		credentialToUpdate.setUserStatus(UserStatus.ACTIVE);
		credentialToUpdate.setUserActivationDate(new Date());
		UserCredentials credId=userRegistrationDAO.updateUserStatus(credentialToUpdate);
		if(credId.getUserStatus().equals(UserStatus.ACTIVE)) {
		return "Your Status is Changed Sucessfully";
		}
		return "Sorry Your Status Not Changed Please Try Again";
		
	}

	
}
