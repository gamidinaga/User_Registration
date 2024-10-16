package com.nt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.nt.common.utils.Country;
import com.nt.common.utils.State;

@Entity
@Table(name = "user_registration")
@NamedQueries({ @NamedQuery(name = "UserRegistration.findAll", query = "SELECT u FROM UserRegistration u") })
public class UserRegistration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 19, nullable = false)
	private int id;

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	private String lastName;

	@Column(name = "DOB", nullable = false)
	private Date dob;

	@Column(name = "EMAIL", nullable = false, length = 50)
	private String email;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "ADDRESS")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "STATE_ID", referencedColumnName = "CODE")
	private State state;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "CODE")
	private Country country;

	@Column(name = "GENDER")
	private String gender;

	@Cascade({ CascadeType.ALL })
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userRegistration")
	private List<UserLanguages> userlanguagesSet = new ArrayList<>();

	@Cascade({ CascadeType.ALL })
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "userRegistration")
	private UserCredentials userCredentials;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public List<UserLanguages> getUserlanguagesSet() {
		return userlanguagesSet;
	}

	public void setUserlanguagesSet(List<UserLanguages> userlanguagesSet) {
		this.userlanguagesSet = userlanguagesSet;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Override
	public String toString() {
		return "UserRegistration [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", state=" + state + ", country="
				+ country + ", gender=" + gender + ", userlanguagesSet=" + userlanguagesSet + ", userCredentials="
				+ userCredentials + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		UserRegistration other = (UserRegistration) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void copyProvidedUserToManagd(UserRegistration providedUser) {
		this.setFirstName(providedUser.firstName);
		this.setLastName(providedUser.lastName);
		this.setDob(providedUser.dob);
		this.setGender(providedUser.gender);
		this.setEmail(providedUser.email);
		this.setPhone(providedUser.phone);
		this.setAddress(providedUser.address);
	}

}
