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

import com.oams.app.dao.NotificationDao;
import com.oams.app.entities.Notification;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Iterable<Notification> getNotificationByPatientId(Integer patientId) {
		List<Notification> notifications =  entityManager.createNamedQuery("getAllPatientsNotification",Notification.class).setParameter(1, patientId).getResultList();
		
		return notifications;
	}

	@Override
	public void addNotification(Notification notification) {
		
		entityManager.persist(notification);
		
	}

	@Override
	public List<Notification> getAllNotification() {
		
		return entityManager.createQuery("from Notification where eventDate >?1 order by id desc").setParameter(1, new Date()).getResultList();
		
	}

	
}
