/**
 * 
 */
package com.oams.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Entity
@Table(name="notification")
@NamedNativeQueries(@NamedNativeQuery(name="getAllPatientsNotification",query="SELECT * FROM notification n INNER JOIN patient_notifications pn ON n.id = pn.notifications_id  WHERE patients_patientid=?1 order by pn.notifications_id desc limit 10",resultClass=Notification.class))
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="notification_desc", length=5000)
	private String notificationDesc;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="patient_notifications",joinColumns=@JoinColumn(name="notifications_id"),inverseJoinColumns=@JoinColumn(name="patients_patientid"))
	private List<Patient> patients = new ArrayList<>();
	
	@Column(name="active")
	private Integer active;

	@Column(name="event_date")
	private Date eventDate;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	
}
