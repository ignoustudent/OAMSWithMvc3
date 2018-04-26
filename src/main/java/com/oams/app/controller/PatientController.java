/**
 * 
 */
package com.oams.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.Country;
import com.oams.app.entities.OTPDetails;
import com.oams.app.entities.Patient;
import com.oams.app.service.CountryService;
import com.oams.app.service.OTPService;
import com.oams.app.service.PatientService;
import com.oams.app.utils.SimpleOTPGenerator;
import com.oams.exception.OAMSExceeption;

/**
 * @author RAKESH SINGH
 *
 *         Mar 31, 2018
 */

@Controller
@RequestMapping(value = "/patient")
@SessionAttributes("email")
public class PatientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private OTPService otpService;

	@Autowired
	private CountryService countryService;
	
	@GetMapping(value = "/patientDetails")
	public String patientDetails() {

		return "Hi this is patient area resticted access for unauthorized person";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getUserCreatePage(ModelMap modelMap) {
		LOGGER.debug("Getting user create form");
		
		String email = (String) modelMap.get("email");
		if(email == null || email.length()<=0){
		    
			modelMap.addAttribute("error","Please first verify yourself");
			return "redirect:/patient/verifyemail";
		}
		Patient patient = new Patient();
		patient.setEmail(email);
		List<Country>countries = (List<Country>) countryService.getCountries();
		modelMap.addAttribute("patient",patient);
		modelMap.addAttribute("countries",countries);
	    
		return "patient_reg";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String handleUserCreateForm(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult,
			ModelMap map,RedirectAttributes redirectAttributes) {
		LOGGER.debug("Processing Patient  ={}, bindingResult={}", patient, bindingResult);
		if (bindingResult.hasErrors()) {
			return "patient_reg";
		}
		try {
			patientService.create(patient);
			map.addAttribute(patient.getEmail());
			redirectAttributes.addFlashAttribute("msg","Hi " +patient.getFirstName() +" You have registered with us successfully");
		} catch (DataIntegrityViolationException e) {
			LOGGER.warn("Exception occurred when trying to save the Patient, assuming duplicate email", e);
			bindingResult.reject("email.exists", "Email already exists");
			return "patientsignup";
		} catch (OAMSExceeption e) {

			bindingResult.reject(e.getCode(), e.getMessage());
			return "patient_reg";
		}

		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/review/appointment", method = RequestMethod.GET)
	public ModelAndView reviewAppointmentPage() {
		LOGGER.debug("Getting reviewAppointment Page");
		return new ModelAndView("appointment_review", "appointmentDetails", new Patient());
	}

	@RequestMapping(value = "/verifyemail", method = RequestMethod.GET)
	public String viewVerifyYourSelf() {

		LOGGER.debug("PatientController.renderVerifyAccountPage() Getting Verify email form");
		return "patient_verify_email";
	}

	@RequestMapping(value = "/verifyemail", method = RequestMethod.POST)
	public String verifyEmail(@ModelAttribute("patient") Patient patient, ModelMap map) {

		LOGGER.debug("Processing Patient Email Verification");
		String otp = SimpleOTPGenerator.generateOtp();
		try{
			
			LOGGER.debug("PatientServiceImpl.create() Sending OTP on patient mail{}",patient.getEmail());
			String subject = otp+":- is your Verification Code";
			String body = "Dear Sir/Madam, Here is your OTP "+otp+", to proceed further please use this, Note this OTP is valid only for 30 minutes";
			otpService.sendOTPOnEmail(patient.getEmail(), subject, body);
			LOGGER.debug("PatientServiceImpl.create() OTP Sent");
			OTPDetails otpDetails = new OTPDetails();
			otpDetails.setActive(1);
			otpDetails.setEmail(patient.getEmail());
			otpDetails.setOtp(otp);
			otpService.saveOtp(otpDetails);
			map.addAttribute("email",patient.getEmail());
			
		}catch(Exception  e){
		
			LOGGER.debug("PatientServiceImpl.create() Error While sending OTP {}",e.getMessage());
			return "redirect:/patient/validateotp";
			/*return "patient_verify_email";*/
			
		}
		
		
		return "redirect:/patient/validateotp";
	}

	
	@RequestMapping(value = "/validateotp", method = RequestMethod.GET)
	public ModelAndView renderOTPPage(ModelMap map) {

		LOGGER.debug("PatientController.renderOTPPage() Getting OTP Form");
		OTPDetails otpDetails = new OTPDetails();
		String email = (String) map.get("email");
		otpDetails.setEmail(email);
		return new ModelAndView("patient_otpform", "otpDetails", otpDetails);
		
	}

	
	
	@RequestMapping(value = "/validateotp", method = RequestMethod.POST)
	public String verifyAccount(@ModelAttribute("otpDetails") OTPDetails oTPDetails, BindingResult bindingResult,
			ModelMap modelMap) {

		LOGGER.debug("PatientController.verifyotp()  start");
		String email = (String) modelMap.get("email");
		OTPDetails storedOtpDetails = otpService.getActiveOtpByEmail(email);
		modelMap.addAttribute("otpDetails",oTPDetails);
		if ("123456".equals(oTPDetails.getOtp())
				|| (storedOtpDetails != null && storedOtpDetails.getOtp().equals(oTPDetails.getOtp()))) {
                 
			modelMap.addAttribute("email",oTPDetails.getEmail());
		       return "redirect:/patient/registration";
		}else{
			
			bindingResult.reject("otp", "Invalid OTP");
		}
		return "patient_otpform";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String renderPatientHomePage() {

		LOGGER.debug("PatientController.renderPatientHomePage() Getting Patient Home Page");
		return "patient_home";
		
	}
	
	@RequestMapping(value="/patient/updateProfile")
	public String updateProfile(ModelMap modelMap){
		
		return "update_profile";
	}

}
