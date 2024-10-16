package com.nt.user.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;

public class UserRegistrationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;

	private String firstName;

	private String lastName;

	private Date dob;

	private String email;
	
	private String phone;
	
	private String address;

	private String stateCode;

	private String countryCode;

	private String gender;

	private String[] langId;
	
	private String langDesc;

	private int userCredId;

	private String username;

	private String password;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getLangId() {
		return langId;
	}

	public void setLangId(String[] langId) {
		this.langId = langId;
	}

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	public int getUserCredId() {
		return userCredId;
	}

	public void setUserCredId(int userCredId) {
		this.userCredId = userCredId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRegistrationDto [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", email=" + email + ", phone=" + phone + ", address=" + address + ", stateCode="
				+ stateCode + ", countryCode=" + countryCode + ", gender=" + gender + ", langId="
				+ Arrays.toString(langId) + ", langDesc=" + langDesc + ", userCredId=" + userCredId + ", username="
				+ username + ", password=" + password + "]";
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
		UserRegistrationDto other = (UserRegistrationDto) obj;
		if (userid != other.userid)
			return false;
		return true;
	}

}
