package com.nt.common.dao.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.common.dao.CommonQueriesDao;
import com.nt.common.utils.Country;
import com.nt.common.utils.State;

@Repository
@Transactional(value = TxType.SUPPORTS)
public class CommonQueriesDaoImpl implements CommonQueriesDao {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> listAllCountry() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.getNamedQuery("Country.findAll");
		return (List<Country>) query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Country> listWithHQLQuery() {
		Session session = sessionFactory.getCurrentSession();
		final Query query = session.createQuery("select c from Country c").setCacheable(true);
		return query.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<State> listAllState() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("State.findAll");
		return (List<State>) query.list();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public Country findCountryByCode(String countryCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Country.findByCode");
		query.setString("code", countryCode);
		return (Country) query.uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public State findStateByCode(String stateCode) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("State.findByCode");
		query.setString("code", stateCode);
		return (State) query.uniqueResult();
	}

}
