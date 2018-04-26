/**
 * 
 */
package com.oams.app.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 *         Apr 7, 2018
 */
@Entity
@Table(name = "appointment_detail")
@NamedNativeQueries(@NamedNativeQuery(name="getAllBookingByDateDeptIdAndHosId",query="select * from appointment_detail where hospital_id=?1 and department_id=?2 and appointment_date>=?3",resultClass=AppointmentDetail.class))
public class AppointmentDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmeent_id")
	private Integer id;

	@Column(name="appointment_date")
	private Date appointmentDate;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch =FetchType.EAGER)
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch =FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department department;
	
	@Transient
	private int deptId;
	
	@Transient
	private int hosId;
	
	@Column(name="city")
	private Integer city;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="country")
	private Integer country;
	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@Column(name="created_on")
	private Date createdOn;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="mo_no")
	private String moNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status")
	private Integer satus;
	
	@Column(name="blood_group")
	private String bloodGroup;
	
	@Column(name="height")
	private Integer height;
	
	@Column(name="wight")
	private Integer weight;
	
	@Column(name="blood_pressure")
	private String bloodPresure;
	
	@Column(name="diseases")
	private String diaseases;
	
	@Column(name="allergies")
	private String allergies;
	
	@Column(name="current_medications")
	private String currentMedications;
	
	@Transient
	private LocalDate appointDateInLocalFormat;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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
	
	
	

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMoNo() {
		return moNo;
	}

	public void setMoNo(String moNo) {
		this.moNo = moNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Integer getSatus() {
		return satus;
	}

	public void setSatus(Integer satus) {
		this.satus = satus;
	}

	
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getHosId() {
		return hosId;
	}

	public void setHosId(int hosId) {
		this.hosId = hosId;
	}

	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@PrePersist
	public void onCreate(){
		this.satus = 1;
		this.createdOn = new Date();
	}
	
	@PreUpdate
	public void onUpdate(){
		
		this.updatedOn = new Date();
	}

	
	public LocalDate getAppointDateInLocalFormat() {
		return appointDateInLocalFormat;
	}

	public void setAppointDateInLocalFormat(LocalDate appointDateInLocalFormat) {
		this.appointDateInLocalFormat = appointDateInLocalFormat;
	}
	
	

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBloodPresure() {
		return bloodPresure;
	}

	public void setBloodPresure(String bloodPresure) {
		this.bloodPresure = bloodPresure;
	}

	public String getDiaseases() {
		return diaseases;
	}

	public void setDiaseases(String diaseases) {
		this.diaseases = diaseases;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getCurrentMedications() {
		return currentMedications;
	}

	public void setCurrentMedications(String currentMedications) {
		this.currentMedications = currentMedications;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointDateInLocalFormat == null) ? 0 : appointDateInLocalFormat.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
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
		AppointmentDetail other = (AppointmentDetail) obj;
		if (appointDateInLocalFormat == null) {
			if (other.appointDateInLocalFormat != null)
				return false;
		} else if (!appointDateInLocalFormat.equals(other.appointDateInLocalFormat))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (hospital == null) {
			if (other.hospital != null)
				return false;
		} else if (!hospital.equals(other.hospital))
			return false;
		return true;
	}

	

	
	
}
