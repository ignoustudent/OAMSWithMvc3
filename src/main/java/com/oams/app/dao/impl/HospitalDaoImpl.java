/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.oams.app.dao.HospitalDao;
import com.oams.app.entities.Department;
import com.oams.app.entities.Hospital;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Repository
@Transactional
public class HospitalDaoImpl implements HospitalDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<Hospital> getHospitalByCityId(Integer id) {
		
		List<Hospital> hospitalList = entityManager.createNamedQuery("getAllHotelByCityId",Hospital.class).setParameter(1, id).getResultList();
		return hospitalList;
	}

	@Override
	public Hospital getHospitalById(Integer id) {
		return entityManager.find(Hospital.class, id);
	}

	
	@Override
	public void addHospital(Hospital hospital) {
		
		if(hospital.getId() != null)
		entityManager.merge(hospital);
		else
		entityManager.persist(hospital);
	}

	
	@Override
	public List<Hospital> geAllHospital() {
		
		return entityManager.createQuery("from Hospital order by id desc").getResultList();
	}

	@Override
	public void updateHospital(Hospital hospital) {
		
		for(Department dept : hospital.getDepartmentList()){
			
			dept.getHospitalList().add(hospital);
		}
		
	   Session session =(Session) entityManager.getDelegate();
	   
		session.saveOrUpdate(hospital);
		session.close();
		
	}

}
