/**
 * 
 */
package com.oams.app.service;

import java.util.List;

import javax.validation.Valid;

import com.oams.app.entities.Notification;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
public interface NotificationService {

	Iterable<Notification> getNotificationByPatientId(Integer patientId);

	void addNotification(@Valid Notification notification);
	List<Notification> getAllNotification();
}
