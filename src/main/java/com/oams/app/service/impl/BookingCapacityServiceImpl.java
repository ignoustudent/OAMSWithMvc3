/**
 * 
 */
package com.oams.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.BookingCapacityDao;
import com.oams.app.entities.BookingCapacity;
import com.oams.app.service.BookingCapacityService;

/**
 * @author RAKESH SINGH
 *
 * Apr 21, 2018
 */
@Service
public class BookingCapacityServiceImpl implements BookingCapacityService{

	@Autowired
	private BookingCapacityDao bookingCapacityDao;
	
	@Override
	public void save(BookingCapacity capacity) {
		
		bookingCapacityDao.save(capacity);
		
	}

	
}
