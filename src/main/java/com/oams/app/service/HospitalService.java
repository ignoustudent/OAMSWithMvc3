/**
 * 
 */
package com.oams.app.service;

import java.util.List;

import javax.validation.Valid;

import com.oams.app.entities.Hospital;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
public interface HospitalService {

	public Iterable<Hospital> getHospitalListByCityId(Integer cityId);

	public void addHospital(Hospital hospital);
	
	public List<Hospital> geAllHospital();

	public Hospital getHospitalById(Integer id);

	
	public void updateHospital(Hospital hospital);
}

