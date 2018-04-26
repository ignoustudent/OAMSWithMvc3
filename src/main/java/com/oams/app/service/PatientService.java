/**
 * 
 */
package com.oams.app.service;

import com.oams.app.entities.Patient;
import com.oams.exception.OAMSExceeption;

/**
 * @author RAKESH SINGH
 *
 * Apr 3, 2018
 */
public interface PatientService {

	public void create(Patient patient) throws OAMSExceeption;
	public Patient getPatientByEmail(String email);
	public void updatePatient(Patient patient);
}
