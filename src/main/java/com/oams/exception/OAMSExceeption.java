/**
 * 
 */
package com.oams.exception;

/**
 * @author RAKESH SINGH
 *
 * Apr 4, 2018
 */
public class OAMSExceeption extends Exception{

	private static final long serialVersionUID = 8898714171390175625L;
	
	private String code;
	
	public OAMSExceeption(String code, String message){
		
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	

}
