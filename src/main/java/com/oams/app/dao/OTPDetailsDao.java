/**
 * 
 */
package com.oams.app.dao;

import com.oams.app.entities.OTPDetails;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
public interface OTPDetailsDao {

   void saveOtp(OTPDetails otp);
   OTPDetails getOtp(String email);
}
