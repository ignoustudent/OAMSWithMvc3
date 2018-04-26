/**
 * 
 */
package com.oams.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 * Apr 7, 2018
 */

@Entity
@Table(name="hospital")
@NamedNativeQueries(@NamedNativeQuery(name="getAllHotelByCityId",query="select * from hospital where city_id=?1",resultClass=Hospital.class))
public class Hospital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="h_name")
	private String hospitanName;
	
	@Column(name="h_address")
	private String hAddress;
	
	@Column(name="h_contact")
	private String hContact;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinTable(name="department_hospital_list", joinColumns=@JoinColumn(name="hospital_list_id"),inverseJoinColumns=@JoinColumn(name="department_list_id"))
	private List<Department> departmentList =new ArrayList<>();

	@OneToMany(cascade=CascadeType.ALL, mappedBy="hospital")
	private List<Doctor>doctors;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHospitanName() {
		return hospitanName;
	}

	public void setHospitanName(String hospitanName) {
		this.hospitanName = hospitanName;
	}

	public String gethAddress() {
		return hAddress;
	}

	public void sethAddress(String hAddress) {
		this.hAddress = hAddress;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	
	public String gethContact() {
		return hContact;
	}

	public void sethContact(String hContact) {
		this.hContact = hContact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
