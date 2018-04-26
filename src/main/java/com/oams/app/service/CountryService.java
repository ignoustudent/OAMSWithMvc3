/**
 * 
 */
package com.oams.app.service;

import java.util.Collection;

import com.oams.app.entities.Country;
import com.oams.app.entities.State;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
public interface CountryService {
	
	public Collection<Country> getCountries();
	public Collection<State> getStateByCountryID(Integer id);

}
