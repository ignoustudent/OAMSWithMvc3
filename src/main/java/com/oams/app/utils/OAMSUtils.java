/**
 * 
 */
package com.oams.app.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author RAKESH SINGH
 *
 * Apr 4, 2018
 */
public class OAMSUtils {

private void sendEmail(String to, String subject,String body) throws MessagingException{
		
		/*MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(to);
		helper.setText(body);
		helper.setSubject(subject);
		javaMailSender.send(message);*/
	}
}
