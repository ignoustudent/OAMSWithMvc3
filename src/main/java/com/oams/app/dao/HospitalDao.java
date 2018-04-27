/**
 * 
 */
package com.oams.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oams.app.entities.Hospital;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
public interface HospitalDao {

	public Iterable<Hospital>getHospitalByCityId(Integer id);
	
	public Hospital getHospitalById(Integer id);
	
	public void addHospital(Hospital hospital);
	
	public List<Hospital> geAllHospital();

	public void updateHospital(Hospital hospital);
}