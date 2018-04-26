/**
 * 
 */
package com.oams.app.service;

import java.util.List;

import com.oams.app.entities.Department;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
public interface DepartmentService {

	
	public Department create(Department department);
	public Iterable<Department> getDepartmentByHospitalId(Integer hotelId);
	
	public List<Department> findAllDepartment();
}
