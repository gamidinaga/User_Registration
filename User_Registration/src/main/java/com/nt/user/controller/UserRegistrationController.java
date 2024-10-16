package com.nt.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nt.command.UserRegistrationCmd;
import com.nt.common.utils.Country;
import com.nt.common.utils.State;
import com.nt.entity.UserCredentials;
import com.nt.entity.UserRegistration;
import com.nt.entity.UserStatus;
import com.nt.user.dto.UserRegistrationDto;
import com.nt.user.service.UserRegistrationService;
import com.nt.validate.FormValidator;

@Controller
@SessionAttributes("countryMap,stateMap")
public class UserRegistrationController {
	// private static final String UPLOADED_DIRECTORY = "E://nag";
	private static Logger logger = Logger.getLogger(UserRegistrationController.class);

	@Autowired
	private UserRegistrationService service;

	@Resource
	private FormValidator validate;

	// To Launch the Registration form
	@RequestMapping(value = "/form.htm", method = RequestMethod.GET)
	public String launchFormPage(@ModelAttribute("userRegistrationCmd") UserRegistrationCmd cmd) {
		logger.info("RegisterForm Launched");
		return "userRegistration";
	}
	
	@RequestMapping(value = "/update_status.htm", method = RequestMethod.GET)
	public String activateUser(Model model,@RequestParam("uId")int userId) {
		 String userStatus=service.updateUserStatus(userId);
		 model.addAttribute("user_status", userStatus);
		return "update_user";
	}

	// To process the Form Data
	@RequestMapping(value = "/form.htm", method = RequestMethod.POST)
	public String registerUser(Model model,
			@ModelAttribute("userRegistrationCmd") UserRegistrationCmd userRegistrationCmd, BindingResult errors)
			throws IOException {
		logger.info(" In Controller:User Is Registring");
		System.out.println("In register");

		// call validate method
		if (validate.supports(UserRegistrationCmd.class)) {
			logger.debug("In Controller:Validate (); Checking Validation Errors ");
			validate.validate(userRegistrationCmd, errors);
			if (errors.hasErrors()) {
				logger.error(errors.getFieldError());
				logger.error(errors.getFieldErrorCount());
				System.out.println(errors.getErrorCount());
				System.out.println(errors.getFieldError());
			} // inner if
		} // outer if

		// instantiate dto class
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		// convert cmd having form data into dto class obj
		BeanUtils.copyProperties(userRegistrationCmd, userRegistrationDto);
		userRegistrationDto.setUsername(userRegistrationCmd.getFirstName() + "" + userRegistrationCmd.getLastName());

		if (userRegistrationCmd.getUserid() ==0) {
			System.out.println("creating " + userRegistrationDto);
			UserRegistration userRegistration = service.createUser(userRegistrationDto);
			// instantiate map obj
			model.addAttribute("createUser_result", userRegistration);
		} else {
			userRegistrationDto.setUserCredId(userRegistrationCmd.getUserCredId());
			userRegistrationDto
					.setUsername(userRegistrationCmd.getFirstName() + "" + userRegistrationCmd.getLastName());
			userRegistrationDto.setPassword(userRegistrationCmd.getPassword());
			logger.info("In Controller: updating User " + userRegistrationCmd.getUserid());
			System.out.println("updating" + userRegistrationDto);
			UserRegistration userRegistration = service.updateUser(userRegistrationDto);
			logger.info("In Controller: updateUser() " + userRegistrationCmd.getUserid());
			model.addAttribute("updatedUser_result", userRegistration);
		}
		return "userRegistration";
	}// process method end

	/*
	 * @RequestMapping(value = "/download.htm") public String
	 * downloadFile(@RequestParam("passport_file") String fname,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception {
	 * 
	 * logger.
	 * info("In Controller: Downloading the file Content from sever machine file System"
	 * ); System.out.println("filename::" + fname); File file = null;
	 * InputStream is = null; OutputStream os = null; // make browser to make
	 * recived content as downloadable file res.addHeader("Content-Disposition",
	 * "attachment;fileName=" + fname); // Locate file file = new
	 * File(UPLOADED_DIRECTORY + "/" + fname); // get File MIME type and set it
	 * as response content type
	 * res.setContentType(req.getServletContext().getMimeType(file.
	 * getAbsolutePath())); // take file length as response content legnth
	 * res.setContentLength((int) file.length()); // create InputStream pointing
	 * to the file to be downloaded is = new FileInputStream(file); // create
	 * OuputStream pointing to the response objs os = res.getOutputStream(); //
	 * wrtie File content to response obj
	 * 
	 * IOUtils.copy(is, os);
	 * 
	 * // flush the response res.flushBuffer();
	 * logger.info("In Controller: File Made As dowanloadable"); // close stream
	 * is.close();
	 * 
	 * os.close(); return null; }// method
	 */
	@RequestMapping(value = "/users_data.htm", method = RequestMethod.GET)
	public String loadAllUsers(Model model) {
		logger.debug("In Controller:Entered into loadUsers()");
		// List<UserRegistrationCmd> userRegistrationCmdList= new ArrayList<>();
		List<UserRegistrationDto> userRegistrationDtoList = null;
		userRegistrationDtoList = service.getAllUsers();
		/*
		 * userRegistrationDtoList.forEach(userRegistrationDto->{
		 * UserRegistrationCmd userRegistrationCmd=new UserRegistrationCmd();
		 * BeanUtils.copyProperties(userRegistrationDto, userRegistrationCmd);
		 * 
		 * userRegistrationCmdList.add(userRegistrationCmd); });
		 */
		System.out.println("in list" + userRegistrationDtoList.size());
		model.addAttribute("usersList", userRegistrationDtoList);
		logger.debug("In Controller: Data Loaded ");
		return "users_data";
	}

	@RequestMapping(value = "edit_user.htm", method = RequestMethod.GET)
	public String loadUserById(Model model, @RequestParam("uId") int userId,
			@ModelAttribute("userRegistrationCmd") UserRegistrationCmd userRegistrationCmd) {
		logger.debug("In Controller:Entered into loadUsers()");

		UserRegistrationDto userRegistrationDto = null;
		userRegistrationDto = service.loadUserById(userId);

		BeanUtils.copyProperties(userRegistrationDto, userRegistrationCmd);
		System.out.println("in Commond " + userRegistrationCmd.toString());
		model.addAttribute("userRegistrationCmd", userRegistrationCmd);
		logger.debug("In Controller: Data Loaded ");
		return "userRegistration";
	}

	/*@RequestMapping(value = "form.htm", method = RequestMethod.POST)
	public String updateUser(Model model,
			@ModelAttribute("userRegistrationCmd") UserRegistrationCmd userRegistrationCmd) {
		if (userRegistrationCmd.getUserid() != 0) {
			UserRegistrationDto userRegistrationDto = null;
			UserRegistration userRegistration = null;
			userRegistrationDto = new UserRegistrationDto();
			BeanUtils.copyProperties(userRegistrationCmd, userRegistrationDto);
			userRegistrationDto
					.setUsername(userRegistrationCmd.getFirstName() + "" + userRegistrationCmd.getLastName());
			logger.info("In Controller: updating User " + userRegistrationCmd.getUserid());
			userRegistration = service.updateUser(userRegistrationDto);
			logger.info("In Controller: updateUser() " + userRegistrationCmd.getUserid());
			model.addAttribute("updatedUser_result", userRegistration);
		}
		return "update_user";
	}*/

	@RequestMapping(value = "delete_user.htm", method = RequestMethod.GET)
	public String removeUser(Model model, @RequestParam("uId") int userId) {
		String result = null;
		logger.info("In Controller: removeUser() is Removing Data Of" + userId);
		result = service.deleteUserById(userId);
		logger.info("In Controller: removeUser() is Removed Data Of" + userId);
		model.addAttribute("deletedUser_result", result);
		return "delete_user";
	}

	@ModelAttribute("countryList")
	public List<Country> populateCountries() {
		// Map<String, List<Countrys_List>> countries = new
		// HashMap<String,List<Countrys_List> >();
		List<Country> countryList;
		countryList = service.findAllCountryList();
		System.out.println(countryList.toString());
		return countryList;
	}

	@ModelAttribute("countryMap")
	public Map<String, String> populateCountrys() {
		Map<String, String> countries = new HashMap<String, String>();
		List<Country> countryList;
		countryList = service.findAllCountryList();
		countryList.forEach(country -> {
			countries.put(country.getCountryCode(), country.getCountryDesc());
		});
		System.out.println(countryList.toString());
		return countries;
	}

	@ModelAttribute("stateList")
	public List<State> populateState() {
		// Map<String, String> states = new HashMap<String, String>();
		List<State> stateList;
		// add states
		stateList = service.findAllStateList();
		System.out.println(stateList.toString());
		return stateList;
	}

	@ModelAttribute("stateMap")
	public Map<String, String> populateStates() {
		Map<String, String> states = new HashMap<String, String>();
		List<State> stateList;
		// add states
		stateList = service.findAllStateList();
		stateList.forEach(state -> {
			states.put(state.getStateCode(), state.getStateDesc());
		});
		System.out.println(states.toString());
		return states;
	}

	@ModelAttribute("langList")
	public Map<String, String> populateLanguage() {
		// List<String> languages = new ArrayList<String>();
		Map<String, String> languages = new HashMap<String, String>();
		// add languages
		languages.put("11", "Telugu");
		languages.put("12", "English");
		languages.put("13", "Hindi");
		return languages;
	}

	@InitBinder
	public void converter(WebDataBinder binder) {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
