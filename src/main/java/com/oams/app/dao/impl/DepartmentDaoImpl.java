/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oams.app.dao.DepartmentDao;
import com.oams.app.entities.Department;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<Department> getHotelDepartment(Integer hotelId) {
		
		List<Department> departments = entityManager.createNamedQuery("getHospitalDepartments",Department.class).setParameter(1,hotelId).getResultList();
		return departments;
	}


	@Override
	public Department getDepartmentById(int id) {
		return entityManager.find(Department.class, id);
	}


	@Override
	public List<Department> findAllDepartment() {
		
		return entityManager.createQuery("from Department").getResultList();
	}

}
