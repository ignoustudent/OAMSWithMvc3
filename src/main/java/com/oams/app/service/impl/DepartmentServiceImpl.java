/**
 * 
 */
package com.oams.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.DepartmentDao;
import com.oams.app.entities.Department;
import com.oams.app.service.DepartmentService;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public Department create(Department department) {
		
		return null;//departmentDao.save(department);
	}

	@Override
	public Iterable<Department> getDepartmentByHospitalId(Integer hotelId) {
		
		return departmentDao.getHotelDepartment(hotelId);
	}

	@Override
	public List<Department> findAllDepartment() {
		
		return departmentDao.findAllDepartment();
	}

}
