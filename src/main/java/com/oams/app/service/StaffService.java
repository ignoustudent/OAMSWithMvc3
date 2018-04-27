/**
 * 
 */
package com.oams.app.service;

import com.oams.app.entities.Staff;

/**
 * @author RAKESH SINGH
 *
 * Apr 20, 2018
 */
public interface StaffService {

	Staff getStaffByEmail(String email);

	
	void updateStaff(Staff staff);
}
