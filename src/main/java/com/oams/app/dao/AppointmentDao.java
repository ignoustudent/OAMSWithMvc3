/**
 * 
 */
package com.oams.app.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.NamedQueries;
import org.springframework.data.repository.CrudRepository;

import com.oams.app.entities.AppointmentDetail;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
public interface AppointmentDao{

public List<AppointmentDetail>getAppointmentDetailByEmail(String email);


public List<AppointmentDetail> getAppointmentsBYHandDID(Integer hospitalId, Integer departmentId, Date date);

public AppointmentDetail appointmentById(Integer id);

public AppointmentDetail save(AppointmentDetail appointmentDetails);


public List<AppointmentDetail> getAllAppointment();

}
