/**
 * 
 */
package com.oams.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.StateDao;
import com.oams.app.entities.City;
import com.oams.app.entities.Country;
import com.oams.app.entities.State;
import com.oams.app.service.StateService;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDao stateDao;

	
	@Override
	public Iterable<City> getCityByStateId(Integer id) {
		
		List<Integer>keys = new ArrayList<>();
		keys.add(id);
		List<State>states = stateDao.findAllById(keys);
		if(states != null && states.size()>0){
		
			return states.get(0).getCities();
		}
		
		return null;
	}
	
	
}
