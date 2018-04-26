/**
 * 
 */
package com.oams.app.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.oams.app.dao.OTPDetailsDao;
import com.oams.app.entities.OTPDetails;
import com.oams.app.service.OTPService;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Service
public class OTPServiceImpl implements OTPService{

	@Autowired
	private OTPDetailsDao otpDetailsDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OTPServiceImpl.class);
	
	@Override
	public void saveOtp(OTPDetails otpDetails) {
	 
		otpDetailsDao.saveOtp(otpDetails);
	}

	
	@Override
	public OTPDetails getActiveOtpByEmail(String email) {
		
		return otpDetailsDao.getOtp(email);
	}

	
	@Override
	public void sendOTPOnEmail(String to, String subject, String body) throws MessagingException {
		
		LOGGER.info("OTPServiceImpl.sendOTPOnEmail() Sending OTP to {}",to);
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(to);
		helper.setText(body);
		helper.setSubject(subject);
		javaMailSender.send(message);
	}

	
}
