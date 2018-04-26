/**
 * 
 */
package com.oams.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.oams.app.controller.PatientController;
import com.oams.app.dao.OTPDetailsDao;
import com.oams.app.entities.OTPDetails;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Repository
@Transactional
public class OTPDetailsDaoImpl implements OTPDetailsDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(OTPDetailsDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.oams.app.dao.OTPDetailsDao#otp(com.oams.app.entities.OTPDetails)
	 */
	@Override
	public void saveOtp(OTPDetails otp) {
		
		 List<OTPDetails> otpList =	entityManager.createQuery("from OTPDetails  where email=?1 and active=?2").setParameter(1, otp.getEmail()).setParameter(2, 1).getResultList();
		 if(otpList != null && otpList.size()>0){
			
			 for(OTPDetails existingOtp:otpList){
				 existingOtp.setActive(0);
			 entityManager.persist(existingOtp);
			 }
			
		 }
		 entityManager.persist(otp);
	}

	/* (non-Javadoc)
	 * @see com.oams.app.dao.OTPDetailsDao#getOtp(java.lang.String)
	 */
	@Override
	public OTPDetails getOtp(String email) {
	
	 LOGGER.info("OTPDetailsDaoImpl.getOtp()  Getting OTP");
	 List<OTPDetails> otpList =	entityManager.createQuery("from OTPDetails  where email=?1 and active=?2").setParameter(1, email).setParameter(2, 1).getResultList();
	 if(otpList != null && otpList.size()>0){
		 LOGGER.info("OTPDetailsDaoImpl.getOtp()  Found OTP");
		 return otpList.get(0);
	 }
	 return null;
	}
	
	
}
