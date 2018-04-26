/**
 * 
 */
package com.oams.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.DoctorDao;
import com.oams.app.entities.Doctor;
import com.oams.app.service.DoctorService;

/**
 * @author RAKESH SINGH
 *
 * Apr 14, 2018
 */
@Service
public class DoctorServiceImpl implements DoctorService {

	
	@Autowired
	private DoctorDao doctorDao;
	
	
	@Override
	public List<Doctor> getDoctoryByHotelAndDeptId(Integer hotelId, Integer departmentId) {
		
		return doctorDao.getDoctoryByHotelAndDeptId(hotelId, departmentId);
	}

	@Override
	public void addDoctor(Doctor doctor) {
		
		doctorDao.addDoctor(doctor);
		
	}

	
	@Override
	public List<Doctor> getAllActiveDoctor() {
	
		return doctorDao.getAllActiveDoctor();
	}

	@Override
	public List<Doctor> getAllRegisteredDoctor() {
		
		return doctorDao.getAllActiveDoctor();
	}

}
