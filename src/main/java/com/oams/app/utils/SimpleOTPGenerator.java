/**
 * 
 */
package com.oams.app.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
public class SimpleOTPGenerator {

    public static String generateOtp() {
    	
        int size = 6;
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            // Generate 20 integers 0..20
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }
    
}