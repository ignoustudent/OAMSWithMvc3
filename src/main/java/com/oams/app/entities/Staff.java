/**
 * 
 */
package com.oams.app.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

import com.oams.app.entities.UserCredential;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */
@Entity
@Table(name="staff")
public class Staff {

	  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="firstname")
	@NotEmpty(message="First Name Required!")
	private String firstName;
	
	@Column(name="middlename")
	private String middleName;
	
	@Column(name="lastname")
	@NotEmpty(message="Last Name Required!")
	private String lastName;
	
	@Column(name="fathername")
	private String fatherName;
	
	@Column(name="mothername")
	private String motherName;
	
	@Column(name="email")
	@NotEmpty(message="Email ID Required!")
	@Email(message="Invalid Email Format")
	private String email;
	
	@Column(name="mobno")
	@NotEmpty(message="Mobile No Required")
	private String mobNo;
	
	@Column(name="address")
	private String address;
	
	@Column(name="zipcode")
	private String zipCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="gender")
    private String gender;
	
	@Column(name="createdon")
	private Date createdOn;
	
	@Column(name="updatedon")
	private Date updatedOn;
	
	@Column(name="createdby")
	private String createdBy;
	
	@Column(name="updatedby")
	private String updatedBy;

	@OneToOne(cascade=CascadeType.ALL,fetch =FetchType.EAGER)
	@JoinColumn(name="usercred_id")
	private UserCredential userCredential;
			
	@Column(name="verified")
	private int verified;
	
	@Column(name="dob")
	private Date dob;
	
	@Lob
	@Column(name="profile_image",columnDefinition="mediumblob")
	private byte[] image;
	
	
	@Transient
	private String otp;
		
	@Transient
	private String base64Image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	
	
}
