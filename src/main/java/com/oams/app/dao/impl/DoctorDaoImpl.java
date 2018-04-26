/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.oams.app.dao.DoctorDao;
import com.oams.app.entities.Doctor;

/**
 * @author RAKESH SINGH
 *
 * Apr 14, 2018
 */
@Repository
@Transactional
public class DoctorDaoImpl implements DoctorDao{

	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Doctor> getDoctoryByHotelAndDeptId(Integer hotelId, Integer departmentId) {
		
		return entityManager.createNamedQuery("getDoctorByHAndDeptId",Doctor.class).setParameter(1,hotelId).setParameter(2, departmentId).getResultList();
	}
	
	@Override
	public void addDoctor(Doctor doctor) {
		
		entityManager.persist(doctor);
		
	}

	
	@Override
	public List<Doctor> getAllActiveDoctor() {
		
		return entityManager.createQuery("from Doctor where enabled=?1 order by id desc").setParameter(1, 1).getResultList();
	}

	
}
