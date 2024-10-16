package com.nt.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nt.command.UserRegistrationCmd;

@Component("formValidate")
public class FormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("support");
		return clazz.isAssignableFrom(UserRegistrationCmd.class);
		
	}

	@Override
	public void validate(Object obj, Errors errors) {
		System.out.println("validate");
		UserRegistrationCmd cmd;
		cmd=(UserRegistrationCmd) obj;
		System.out.println(cmd.toString());
		
		if( cmd.getFirstName()==null || cmd.getFirstName().equals("")){
			System.out.println("farstname");
			   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","firstName.required");
			  // System.out.println(errors.getFieldError());
			   
		}
		else if(cmd.getLastName()==null || cmd.getLastName().equals("")) {
			System.out.println("lastname");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","lastName.required");
			    errors.reject("lastName", "lastName.required");
			   
			   
		}
		else if(cmd.getEmail()==null || cmd.getEmail().equals("")) {
			System.out.println("email");
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email.required");
			  
		}
		else if(cmd.getPassword()==null ||cmd.getPassword().equals("") ) {
			System.out.println("pass");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required");
			
		}
		 
		 if(!cmd.getPassword().equals(cmd.getConformPassword())) {
		   System.out.println("confpass");
		   errors.reject("conformPassword","conformPassword.required");
         
		   //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"conformPassword","conformPassword.required");
		}
		 }
		 }
	


