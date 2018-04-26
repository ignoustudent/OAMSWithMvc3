/**
 * 
 */
package com.oams.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oams.app.controller.PatientController;
import com.oams.app.dao.PatientDao;
import com.oams.app.entities.OTPDetails;
import com.oams.app.entities.Patient;
import com.oams.app.entities.Role;
import com.oams.app.entities.UserCredential;
import com.oams.app.service.OTPService;
import com.oams.app.service.PatientService;
import com.oams.app.utils.SimpleOTPGenerator;
import com.oams.exception.OAMSExceeption;

/**
 * @author RAKESH SINGH
 *
 * Apr 3, 2018
 */
@Service
public class PatientServiceImpl implements PatientService{

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OTPService otpService;
	
	@Override
	public void create(Patient patient) throws OAMSExceeption {
		
		
		LOGGER.info("PatientServiceImpl.create() Persisting Patient  Details");
		UserCredential  userCredential = patient.getUserCredential();
		if(userCredential !=null){
			if(!userCredential.getPassword().equals(userCredential.getConfirmPassword())){
				LOGGER.error("PatientServiceImpl.create(): Password Mismatch throwing exception");
				throw new OAMSExceeption("userCredential.password", "Password Mismatch");
			
			}
			userCredential.setPassword(bCryptPasswordEncoder.encode(userCredential.getPassword()));
			userCredential.setRole(Role.PATIENT);
		}
		patientDao.save(patient);
		
		/*LOGGER.debug("PatientServiceImpl.create() Generating OTP for verrification");
		SimpleOTPGenerator otpGenerator = new SimpleOTPGenerator();
		String otp = otpGenerator.generateOtp();
		try{
			
			LOGGER.debug("PatientServiceImpl.create() Sending OTP on patient mail{}",patient.getEmail());
			String subject = otp+":- is your Verification Code";
			String body = "Dear Sir/Madam, Here is your OTP "+otp+", to proceed further please use this, Note this OTP is valid only for 30 minutes";
			otpService.sendOTPOnEmail(patient.getEmail(), subject, body);
			LOGGER.debug("PatientServiceImpl.create() OTP Sent");
			OTPDetails otpDetails = new OTPDetails();
			otpDetails.setActive(1);
			otpDetails.setEmail(patient.getEmail());
			otpDetails.setOtp(otp);
			otpService.saveOtp(otpDetails);
			
		}catch(Exception  e){
		
			LOGGER.debug("PatientServiceImpl.create() Error While sending OTP {}",e.getMessage());
			
		}*/
		
	}

	
	@Override
	public Patient getPatientByEmail(String email) {
		LOGGER.debug("PatientServiceImpl.create() Getting Patient By Email");
		return patientDao.getPatientByEmail(email);
		
	}

	@Override
	public void updatePatient(Patient patient) {
		
		LOGGER.info("PatientServiceImpl.create() Updating patient");
		patientDao.save(patient);
	}

}
