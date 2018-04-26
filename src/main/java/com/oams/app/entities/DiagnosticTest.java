/**
 * 
 */
package com.oams.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
@Entity
@Table(name="diagnostic_test")
public class DiagnosticTest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="test_name", unique=true)
	private String testName;
	
	@Column(name="test_desc")
	private String testDesc;
	
	@Column(name="test_estimated_charges")
	private String testEstimatedCharges;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDesc() {
		return testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}

	public String getTestEstimatedCharges() {
		return testEstimatedCharges;
	}

	public void setTestEstimatedCharges(String testEstimatedCharges) {
		this.testEstimatedCharges = testEstimatedCharges;
	}
	
	
	

}
