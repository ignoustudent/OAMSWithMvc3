/**
 * 
 */
package com.oams.app.dao;

import java.util.List;

import com.oams.app.entities.Doctor;

/**
 * @author RAKESH SINGH
 *
 * Apr 14, 2018
 */
public interface DoctorDao {

	public List<Doctor> getDoctoryByHotelAndDeptId(Integer hotelId, Integer departmentId);

	public void addDoctor(Doctor doctor);
	
	public List<Doctor> getAllActiveDoctor();
}
