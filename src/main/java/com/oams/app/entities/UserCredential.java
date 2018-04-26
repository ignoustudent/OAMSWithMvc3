/**
 * 
 */
package com.oams.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Entity
@Table(name="usercredential")
@Inheritance(strategy=InheritanceType.JOINED)
public class UserCredential implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid", nullable=false, updatable=false)
	private Integer id;
	
	@Column(name="username")
	private String userName;
	
	@NotEmpty(message="Password Can not be blank")
	@Column(name="password",length=1000, nullable=false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name="isactive")
	private Integer isActive;
	
	@Column(name="createdon")
	private Date createdOn;
	
	@Column(name="updatedon")
	private Date updatedOn;
	
	@Column(name="createdby")
	private String createdBy;
	
	@Column(name="updatedby")
	private String updatedBy;

	@JsonIgnore
	@OneToOne(mappedBy="userCredential")
	private Patient patient;
	
	@JsonIgnore
	@OneToOne(mappedBy="userCredential")
	private Staff staff;
	
	@Transient
	private String otp;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@PrePersist
	private void onCreate(){
		
		createdOn = new Date();
		isActive = 1;
	}
	
	@PreUpdate
	private void onUpdate(){
		
		updatedOn = new Date();
	}
	
	
}
