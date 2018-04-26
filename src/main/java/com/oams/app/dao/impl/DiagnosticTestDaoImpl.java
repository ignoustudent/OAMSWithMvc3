/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.DiagnosticTest;
import com.oams.app.entities.DiagnosticTestAppointmentDetail;
import com.oams.app.service.dao.DiagnosticTestDao;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
@Repository
@Transactional
public class DiagnosticTestDaoImpl implements DiagnosticTestDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DiagnosticTest> getAllDiagnosisTest() {
		
		return entityManager.createQuery("from DiagnosticTest").getResultList();
	}

	
	@Override
	public void saveDiagnosticTestAppointment(DiagnosticTestAppointmentDetail details) {
		
		 entityManager.persist(details);
	}

	@Override
	public DiagnosticTestAppointmentDetail getAppointmentById(Integer id) {
		
		return entityManager.find(DiagnosticTestAppointmentDetail.class, id);
	}


	@Override
	public List<DiagnosticTestAppointmentDetail> getAppointmentDetailByEmail(String email) {

		return entityManager.createQuery("from DiagnosticTestAppointmentDetail where patient.email=?1 order by id desc").setParameter(1,email).getResultList();
	}

	
}
