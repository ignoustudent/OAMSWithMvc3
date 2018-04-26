/**
 * 
 */
package com.oams.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.HospitalDao;
import com.oams.app.entities.Department;
import com.oams.app.entities.Hospital;
import com.oams.app.service.HospitalService;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
@Service
public class HospitalServiceImpl implements HospitalService{

	@Autowired
	private HospitalDao hospitalDao;
	@Override
	
	public Iterable<Hospital> getHospitalListByCityId(Integer cityId) {
		
		return hospitalDao.getHospitalByCityId(cityId);
	}

	@Override
	public void addHospital(Hospital hospital) {
		
		List<Department> departments = hospital.getDepartmentList();
		for(Department dept : departments){
			
			dept.getHospitalList().add(hospital);
		}
		
		hospitalDao.addHospital(hospital);
	  
	}

	@Override
	public List<Hospital> geAllHospital() {
		
		return hospitalDao.geAllHospital();
	}

	@Override
	public Hospital getHospitalById(Integer id) {
		
		return hospitalDao.getHospitalById(id);
	}

}
