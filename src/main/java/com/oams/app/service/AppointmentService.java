/**
 * 
 */
package com.oams.app.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.oams.app.entities.AppointmentDetail;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
public interface AppointmentService {

	public AppointmentDetail bookAppointMent(AppointmentDetail appointmentDetails);
	public List<AppointmentDetail>getAppointmentDetailByEmail(String email);
	public AppointmentDetail getAppointmentById(Integer id);
	public List<LocalDate> getAvailability(Integer hospitalId, Integer departmentId);
	public List<AppointmentDetail> getAllAppointment();
}


