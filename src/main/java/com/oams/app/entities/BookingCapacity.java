/**
 * 
 */
package com.oams.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author RAKESH SINGH
 *
 * Apr 12, 2018
 */
@Entity
@Table(name="booking_capacity")
@NamedNativeQueries(@NamedNativeQuery(name="getCapacityByHosAndDeptId",query="select * from booking_capacity where hotel_id=?1 and department_id=?2",resultClass=BookingCapacity.class))
public class BookingCapacity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="max_booking_per_day")
	private Integer maxBookingPerDay;
	
	@OneToOne
	@JoinColumn(name="hotel_id")
	private Hospital hospital;
	
	@OneToOne
	@JoinColumn(name="department_id")
	private Department department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxBookingPerDay() {
		return maxBookingPerDay;
	}

	public void setMaxBookingPerDay(Integer maxBookingPerDay) {
		this.maxBookingPerDay = maxBookingPerDay;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

	
	
	
}
