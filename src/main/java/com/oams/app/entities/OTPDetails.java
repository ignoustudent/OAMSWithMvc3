/**
 * 
 */
package com.oams.app.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Entity
@Table(name="user_otp")
public class OTPDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="otp",nullable =false)
	private String otp;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="creation_time")
	private Date creationTime;
	
	@Column(name="expiration_time")
	private Date expirationTime;
	
	@Column(name="active")
	private int active;

	/**
	 * @return the id
	 */
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
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @return the expirationTime
	 */
	public Date getExpirationTime() {
		return expirationTime;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
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

	@PrePersist
	public void onCreate(){
		
		creationTime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(creationTime);
		calendar.add(Calendar.MINUTE, 30);
		expirationTime =calendar.getTime();
	}
	
}
