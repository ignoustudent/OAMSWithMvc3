/**
 * 
 */
package com.oams.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oams.app.entities.City;
import com.oams.app.entities.Country;
import com.oams.app.entities.State;
import com.oams.app.service.CountryService;
import com.oams.app.service.StateService;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
@Controller
public class StaticDataController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private StateService stateService;
	
	@ResponseBody
	@RequestMapping(value="/getCountries")
	public Collection<Country> getCountries(){
		
		return countryService.getCountries();
	}
	
	@ResponseBody
	@RequestMapping(value="/stateByCountryID")
	public Collection<State> getStateByCountryID(@RequestParam String countryId){
		
		Integer id = Integer.valueOf(countryId);
		return countryService.getStateByCountryID(id);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cityByStateId")
	public Iterable<City> getStateByCountryID(@RequestParam Integer stateId){
		
		
		return stateService.getCityByStateId(stateId);
	}
	
}
