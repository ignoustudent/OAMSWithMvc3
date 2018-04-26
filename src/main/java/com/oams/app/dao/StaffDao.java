/**
 * 
 */
package com.oams.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oams.app.entities.Staff;

/**
 * @author RAKESH SINGH
 *
 * Apr 20, 2018
 */
@Repository
public interface StaffDao extends JpaRepository<Staff, Integer> {

	
	Staff getStaffByEmail(String email);

}
