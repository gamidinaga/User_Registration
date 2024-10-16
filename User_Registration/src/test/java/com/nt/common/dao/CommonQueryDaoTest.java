package com.nt.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nt.common.utils.Country;
import com.nt.common.utils.State;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonQueryDaoTest {
	@Autowired
	CommonQueriesDao commonQueriesDao;

	@Test
	@Ignore
	@Transactional
	public void testListAllCountry() {
		List<Country> countryList = commonQueriesDao.listAllCountry();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commonQueriesDao.listAllCountry();
		commonQueriesDao.listAllCountry();
		Assert.assertNotNull(countryList);
		Assert.assertEquals(5, countryList.size());
	}

	@Test
	@Transactional
	public void testListWithHQLQuery() {
		List<Country> countryList = commonQueriesDao.listWithHQLQuery();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countryList=commonQueriesDao.listWithHQLQuery();

		countryList=commonQueriesDao.listWithHQLQuery();

		Assert.assertNotNull(countryList);
		Assert.assertEquals(6, countryList.size());
	}

	@Test
	@Ignore
	@Transactional
	public void testListAllState() {
		List<State> stateList = commonQueriesDao.listAllState();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commonQueriesDao.listAllState();
		
		commonQueriesDao.listAllState();
		Assert.assertNotNull(stateList);
		Assert.assertEquals(15, stateList.size());
	}

	@Test
	@Ignore
	@Transactional
	public void testFindCountryByCode() {
		Country country = commonQueriesDao.findCountryByCode("IN");
		Assert.assertNotNull(country);
		Assert.assertEquals("India", country.getCountryDesc());
		Assert.assertTrue(country.getCountryDesc().equalsIgnoreCase("INDIA"));
	}

	@Test
	@Ignore
	@Transactional
	public void testFindCountryByCodeWithOutManadatoryFileds() {
		Country country = commonQueriesDao.findCountryByCode(null);
		Assert.assertNull(country);
	}

	@Test
	@Ignore
	@Transactional
	public void testFindStateByCodeWithOutManadatoryFileds() {
		State state = commonQueriesDao.findStateByCode(null);
		Assert.assertNull(state);
	}

}
