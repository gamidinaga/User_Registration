package com.nt.common.utils;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "REF_STATE")
@NamedQueries({
		@NamedQuery(name = "State.findAll", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true") }, query = "SELECT s FROM State s"),
		@NamedQuery(name = "State.findByCode", query = "SELECT s FROM State s WHERE s.stateCode = :code"), })
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE")
	private String stateCode;

	@Column(name = "DESCRIPTION")
	private String stateDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", stateCode=" + stateCode + ", stateDesc=" + stateDesc + "]";
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
		State other = (State) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
