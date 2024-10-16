package com.nt.command;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCmd {

	private int userid;

	private String firstName;

	private String lastName;

	private String email;

	private Date dob;

	private String phone;

	private String address;

	private String countryCode;

	private String stateCode;

	private String[] langId;

	private String gender;
	// private MultipartFile passportfile;
	private String password;

	private String conformPassword;

	private int userCredId;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String[] getLangId() {
		return langId;
	}

	public void setLangId(String[] langId) {
		this.langId = langId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConformPassword() {
		return conformPassword;
	}

	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}

	public int getUserCredId() {
		return userCredId;
	}

	public void setUserCredId(int userCredId) {
		this.userCredId = userCredId;
	}

	@Override
	public String toString() {
		return "UserRegistrationCmd [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dob=" + dob + ", phone=" + phone + ", address=" + address + ", countryCode="
				+ countryCode + ", stateCode=" + stateCode + ", langId=" + Arrays.toString(langId) + ", gender="
				+ gender + ", password=" + password + ", conformPassword=" + conformPassword + ", userCredId="
				+ userCredId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegistrationCmd other = (UserRegistrationCmd) obj;
		if (userid != other.userid)
			return false;
		return true;
	}

}
