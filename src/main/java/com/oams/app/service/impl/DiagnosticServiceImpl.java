/**
 * 
 */
package com.oams.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.DiagnosticTest;
import com.oams.app.entities.DiagnosticTestAppointmentDetail;
import com.oams.app.service.DiagnosticTestService;
import com.oams.app.service.dao.DiagnosticTestDao;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
@Service
public class DiagnosticServiceImpl implements DiagnosticTestService {

	@Autowired
	private DiagnosticTestDao diagnosticTestDao;
	
	@Override
	public List<DiagnosticTest> getAllDiagnosisTest() {
		
		return diagnosticTestDao.getAllDiagnosisTest();
	}

	
	@Override
	public void saveDiagnosticTestAppointment(DiagnosticTestAppointmentDetail details) {
	
	  diagnosticTestDao.saveDiagnosticTestAppointment(details);
	}


	
	@Override
	public DiagnosticTestAppointmentDetail getAppointmentById(Integer id) {
		
		return diagnosticTestDao.getAppointmentById(id);
	}


	@Override
	public List<DiagnosticTestAppointmentDetail> getAppointmentDetailByEmail(String email) {
		
		return diagnosticTestDao.getAppointmentDetailByEmail(email);
	}

}
