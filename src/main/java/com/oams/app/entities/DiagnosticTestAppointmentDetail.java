/**
 * 
 */
package com.oams.app.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
@Entity
@Table(name="diagnostic_test_appointment")
public class DiagnosticTestAppointmentDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@OneToOne(cascade=CascadeType.MERGE,fetch =FetchType.EAGER)
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	
	@Column(name="appointment_date")
	private Date appointmentDate;

	@Column(name="tests")
	private String tests;
	
	@Transient
	private List<String> testList;
	
	
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
	
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="country_id")
	private Country country;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="state_id")
	private State state;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="city_id")
	private City city;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public List<String> getTestList() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	
}
