/**
 * 
 */
package com.oams.app.service.dao;

import java.util.List;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.DiagnosticTest;
import com.oams.app.entities.DiagnosticTestAppointmentDetail;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
public interface DiagnosticTestDao {

	/**
	 * @return
	 */
	public List<DiagnosticTest> getAllDiagnosisTest();

	public void saveDiagnosticTestAppointment(DiagnosticTestAppointmentDetail details);

	public DiagnosticTestAppointmentDetail getAppointmentById(Integer id);

	public List<DiagnosticTestAppointmentDetail> getAppointmentDetailByEmail(String email);
		
}
