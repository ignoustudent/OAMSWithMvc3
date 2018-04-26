/**
 * 
 */
package com.oams.app.service;

import com.oams.app.entities.City;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */
public interface StateService {

	public Iterable<City>getCityByStateId(Integer id);
}
