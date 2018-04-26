/**
 * 
 */
package com.oams.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.oams.app.entities.UserCredential;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
public interface UserCredService extends UserDetailsService {
    
	UserDetails loadUserByUsername(String email);
	
	UserCredential updatePassword(String email, String password);
	
	
}
