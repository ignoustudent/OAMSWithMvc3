/**
 * 
 */
package com.oams.app.utils;

import java.util.Random;

/**
 * @author RAKESH SINGH
 *
 * Apr 2, 2018
 */
public class TestUtils {

	public static void main(String[] args) {
		
		int maximum = 1;
		int minimum=0;
		Random rn = new Random();
		int range = maximum - minimum ;
		int randomNum =  rn.nextInt(range) + minimum;
		System.out.println(randomNum);
	}
}

class Employee{
	
	private String name;
	private String address;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
		
}
