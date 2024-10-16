package com.nt.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nt.common.dao.CommonQueriesDao;
import com.nt.common.utils.Country;
import com.nt.common.utils.State;
import com.nt.entity.UserCredentials;
import com.nt.entity.UserRegistration;

@Repository
@Transactional
public class UserRegistrationDAOImpl implements UserRegistrationDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	CommonQueriesDao commonQueriesDao;

	@Override
	public UserRegistration createUser(UserRegistration userRegistration) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("in dao :saving" );
		session.save(userRegistration);
		tx.commit();
		session.close();
		System.out.println("saved" + userRegistration.getId());
		return userRegistration;
	}

	@SuppressWarnings({ "unchecked","rawtypes" })
	@Override
	public List<UserRegistration> retrieveAllUsers() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("UserRegistration.findAll");
		List<UserRegistration> list= query.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public int deleteUser(int userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UserRegistration userRegistration = session.load(UserRegistration.class, userId);
		session.remove(userRegistration);
		tx.commit();
		session.close();
		return userId;
	}

	@Override
	public void updateUser(UserRegistration userRegistration) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//UserRegistration managedUser=new UserRegistration();
		UserRegistration managedUser = session.load(UserRegistration.class, userRegistration.getId());
		Country country = commonQueriesDao.findCountryByCode(userRegistration.getCountry().getCountryCode());
		State state = commonQueriesDao.findStateByCode(userRegistration.getState().getStateCode());
		managedUser.setState(state);
		managedUser.setCountry(country);
		managedUser.setUserlanguagesSet(userRegistration.getUserlanguagesSet());
		managedUser.setUserCredentials(userRegistration.getUserCredentials());
		managedUser.copyProvidedUserToManagd(userRegistration);
		session.merge(managedUser);
		//session.update(managedUser);
		System.out.println("userdto is saving dao ");
		tx.commit();
		System.out.println("updated");
		session.close();
	}

	@Override
	public UserRegistration loadUserById(int userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UserRegistration userRegistration=session.get(UserRegistration.class,userId);
		tx.commit();
		/*System.out.println(" user loaded"+userRegistration.toString());*/
		return userRegistration;
	}

	

	@Override
	public  UserCredentials updateUserStatus(UserCredentials userCredentials) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//UserCredentials credentialsToUpdate = session.load(UserCredentials.class, userCredentials.getId());
		Object userId=  session.merge(userCredentials);
		tx.commit();
		return (UserCredentials) userId;
	}

	


}
