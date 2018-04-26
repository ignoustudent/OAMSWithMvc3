/**
 * 
 */
package com.oams.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oams.app.entities.Department;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */

public interface DepartmentDao  {
	
	public Iterable<Department>getHotelDepartment(Integer hotelId);
	public Department getDepartmentById(int id);
	
	public List<Department> findAllDepartment();

}
