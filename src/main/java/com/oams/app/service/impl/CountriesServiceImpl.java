/**
 * 
 */
package com.oams.app.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.CountryDao;
import com.oams.app.entities.Country;
import com.oams.app.entities.State;
import com.oams.app.service.CountryService;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
@Service
public class CountriesServiceImpl implements CountryService{

	@Autowired
	private CountryDao countryDao;
	
	@Override
	public Collection<Country> getCountries() {
	
		return (Collection<Country>) countryDao.findAll();
	}

	@Override
	public Collection<State> getStateByCountryID(Integer id) {
		
		List<Integer>keys = new ArrayList<>();
		keys.add(id);
		List<Country>countries = countryDao.findAllById(keys);
		if(countries != null && countries.size()>0){
		
			return countries.get(0).getStateList();
		}
		
		return null;
	}
	
	

}
