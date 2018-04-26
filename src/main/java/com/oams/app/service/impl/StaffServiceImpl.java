/**
 * 
 */
package com.oams.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.StaffDao;
import com.oams.app.entities.Staff;
import com.oams.app.service.StaffService;

/**
 * @author RAKESH SINGH
 *
 * Apr 20, 2018
 */
@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public Staff getStaffByEmail(String email) {
		
		return staffDao.getStaffByEmail(email);
	}

}
