/**
 * 
 */
package com.oams.app.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.NotificationDao;
import com.oams.app.dao.PatientDao;
import com.oams.app.entities.Notification;
import com.oams.app.entities.Patient;
import com.oams.app.service.NotificationService;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private PatientDao patientDao;
	
	@Override
	public Iterable<Notification> getNotificationByPatientId(Integer patientId) {
		
		return notificationDao.getNotificationByPatientId(patientId);
	}

	@Override
	public void addNotification(@Valid Notification notification) {
		 
		List<Patient> patientList = patientDao.findAll();
		for(Patient patient : patientList){
			
			notification.getPatients().add(patient);
			patient.getNotifications().add(notification);
		}
		notificationDao.addNotification(notification);
	}

	
	@Override
	public List<Notification> getAllNotification() {
		
		return notificationDao.getAllNotification();
	}

}
