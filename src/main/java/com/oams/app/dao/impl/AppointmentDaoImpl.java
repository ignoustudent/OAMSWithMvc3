/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.oams.app.dao.AppointmentDao;
import com.oams.app.entities.AppointmentDetail;

/**
 * @author RAKESH SINGH
 *
 * Apr 17, 2018
 */
@Repository
@Transactional
public class AppointmentDaoImpl implements AppointmentDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<AppointmentDetail> getAppointmentDetailByEmail(String email) {

		return entityManager.createQuery("from AppointmentDetail where email=?1").setParameter(1, email).getResultList();
	}

	
	@Override
	public List<AppointmentDetail> getAppointmentsBYHandDID(Integer hospitalId, Integer departmentId, Date date) {
	
		List<AppointmentDetail> details = entityManager.createNamedQuery("getAllBookingByDateDeptIdAndHosId",AppointmentDetail.class).setParameter(1, hospitalId).setParameter(2, departmentId).setParameter(3, date).getResultList();
		return details;
	}


	@Override
	public AppointmentDetail appointmentById(Integer id) {
		
		return entityManager.find(AppointmentDetail.class, id);
	}

	@Override
	public AppointmentDetail save(AppointmentDetail appointmentDetails) {
		
		entityManager.persist(appointmentDetails);
		return appointmentDetails;
	}


	@Override
	public List<AppointmentDetail> getAllAppointment() {

		return entityManager.createQuery("from AppointmentDetail").getResultList();
	}
	}
