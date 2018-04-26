/**
 * 
 */
package com.oams.app.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oams.app.utils.SimpleOTPGenerator;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Controller
public class MailController {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@RequestMapping(value="/sendotp")
	@ResponseBody
	public String sendOtp(@RequestParam String email){
		
		try{
			String otp = SimpleOTPGenerator.generateOtp();
			
			String subject = otp+":- is your Verification Code";
			String body = "Dear Sir/Madam, Here is your OTP "+otp+", to proceed further please use this, Note this OTP is valid only for 30 minutes";
			sendEmail(email,subject,body);
			
			return "OTP Sent!";
		}catch(Exception e){
			
			return "error in sending mail"+e.getMessage();
		}
		
	}
	
	private void sendEmail(String to, String subject,String body) throws MessagingException{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(to);
		helper.setText(body);
		helper.setSubject(subject);
		javaMailSender.send(message);
	}
}
