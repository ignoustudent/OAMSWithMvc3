/**
 * 
 */
package com.oams.app.service;

import javax.mail.MessagingException;

import com.oams.app.entities.OTPDetails;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
public interface OTPService {

	void saveOtp(OTPDetails otpDetails);
	OTPDetails getActiveOtpByEmail(String email);
	void sendOTPOnEmail(String to, String subject,String body) throws MessagingException;
	
}
