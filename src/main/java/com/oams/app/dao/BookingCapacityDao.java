/**
 * 
 */
package com.oams.app.dao;

import com.oams.app.entities.BookingCapacity;

/**
 * @author RAKESH SINGH
 *
 * Apr 17, 2018
 */
public interface BookingCapacityDao {

	public BookingCapacity getCapacityByHosAndDeptId(Integer hospitalId,Integer deptId);

	public void save(BookingCapacity capacity);
}
