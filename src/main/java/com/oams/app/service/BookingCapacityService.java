/**
 * 
 */
package com.oams.app.service;

import java.util.List;

import com.oams.app.entities.BookingCapacity;

/**
 * @author RAKESH SINGH
 *
 * Apr 21, 2018
 */
public interface BookingCapacityService {

	public void save(BookingCapacity capacity);

	
	public BookingCapacity getCapacityByHandDId(Integer id, Integer id2);

	public List<BookingCapacity> getAllCapacity();
}
