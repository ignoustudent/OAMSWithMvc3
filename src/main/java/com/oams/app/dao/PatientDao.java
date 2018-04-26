/**
 * 
 */
package com.oams.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oams.app.entities.Patient;

/**
 * @author RAKESH SINGH
 *
 * Apr 3, 2018
 */
@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {

	public Patient getPatientByEmail(String email);
}
