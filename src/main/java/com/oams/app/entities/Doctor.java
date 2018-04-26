/**
 * 
 */
package com.oams.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Entity
@Table(name="doctor")
@NamedNativeQueries(@NamedNativeQuery(name="getDoctorByHAndDeptId",query="SELECT * FROM doctor WHERE hospital_id =?1 AND department_id=?2",resultClass=Doctor.class))
public class Doctor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name",length=50, nullable=false)
	private String firstName;
	
	@Column(name="last_name", length=50, nullable=false)
	private String lastName;
	
	@Column(name="mob", length=10)
	private String mobNumber;
	
	@Column(name="qualification",length=500,nullable=false)
	private String qualification;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updateBy;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department department;
	
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mobNumber
	 */
	public String getMobNumber() {
		return mobNumber;
	}

	/**
	 * @param mobNumber the mobNumber to set
	 */
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
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

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	@PrePersist
	public void onCreate(){
		this.enabled = 1;
		createdOn = new Date();
	}
	
	@PreUpdate
	public void onUpdate(){
		
		updatedOn = new Date();
	}
}
