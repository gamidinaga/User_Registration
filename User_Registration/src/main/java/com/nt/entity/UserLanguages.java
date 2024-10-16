package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_languages")
public class UserLanguages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length =19)
	private int id;

	@Column(name = "LANG_ID", length = 20, nullable = false)
	private int langId;

	@Column(name = "LANG_DESC", length = 40, nullable = false)
	private String langDesc;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserRegistration userRegistration;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	public UserRegistration getUserReg() {
		return userRegistration;
	}

	public void setUserReg(UserRegistration userReg) {
		this.userRegistration = userReg;
	}

	@Override
	public String toString() {
		return "UserLanguages [id=" + id + ", langId=" + langId + ", langDesc=" + langDesc + ", userReg="
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
		UserLanguages other = (UserLanguages) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
