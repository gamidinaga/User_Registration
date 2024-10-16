package com.nt.common.dao;

import java.util.List;

import com.nt.common.utils.Country;
import com.nt.common.utils.State;

public interface CommonQueriesDao {

	List<Country> listAllCountry();

	List<State> listAllState();

	List<Country> listWithHQLQuery();

	Country findCountryByCode(String countryCode);

	State findStateByCode(String stateCode);
}
