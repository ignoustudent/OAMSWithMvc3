/**
 * 
 */
package com.oams.app.dao;

import java.util.List;

import javax.validation.Valid;

import com.oams.app.entities.Notification;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
public interface NotificationDao {

	Iterable<Notification> getNotificationByPatientId(Integer patientId);
	
	void addNotification(Notification notification);

	List<Notification> getAllNotification();
}
