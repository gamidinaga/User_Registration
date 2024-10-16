package com.nt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_credentials")
public class UserCredentials implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 19)
	private int id;

	@Column(name = "user_name", length = 100, nullable = false, updatable = false)
	private String username;
	@Column(name = "user_password", length = 50, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_status", length = 10, nullable = false)
	private UserStatus userStatus;
	
	@Column(name = "user_auth_value", length = 50, nullable = false)
	private String authValue;
	
	@Column(name = "CREATED_DATE")
	private Date userCreatedDate;
	@Column(name = "UPDATED_DATE")
	private Date userUpdatedDate;
	@Column(name = "ACTIVATION_DATE")
	private Date userActivationDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserRegistration userRegistration;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getAuthValue() {
		return authValue;
	}

	public void setAuthValue(String authValue) {
		this.authValue = authValue;
	}

	public Date getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

	public Date getUserUpdatedDate() {
		return userUpdatedDate;
	}

	public void setUserUpdatedDate(Date userUpdatedDate) {
		this.userUpdatedDate = userUpdatedDate;
	}

	public Date getUserActivationDate() {
		return userActivationDate;
	}

	public void setUserActivationDate(Date userActivationDate) {
		this.userActivationDate = userActivationDate;
	}

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	@Override
	public String toString() {
		return "UserCredentials [id=" + id + ", username=" + username + ", password=" + password + ", userRegistration="
				+ userRegistration + "]";
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
		UserCredentials other = (UserCredentials) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
