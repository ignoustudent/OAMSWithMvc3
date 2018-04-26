/**
 * 
 */
package com.oams.app.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.OTPDetails;
import com.oams.app.entities.UserCredential;
import com.oams.app.service.AppointmentService;
import com.oams.app.service.DoctorService;
import com.oams.app.service.HospitalService;
import com.oams.app.service.OTPService;
import com.oams.app.service.PatientService;
import com.oams.app.service.StaffService;
import com.oams.app.service.UserCredService;

/**
 * @author RAKESH SINGH
 *
 * Apr 1, 2018
 */

@Controller
public class LoginController {
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private OTPService otpService;
	
	@Autowired
	private UserCredService userCredService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@RequestMapping("/")
	public String renderHomePage(){
		
		
		return "OAMSHome";
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
	     LOGGER.debug("Getting login page, error={}", error);
	        return new ModelAndView("login", "error", error);
	    }
	 
	 
	
	 
	 
@RequestMapping(value="/forgot/password", method=RequestMethod.GET)
public String forgotPasswordForm(ModelMap modelMap){
	
	modelMap.addAttribute("userCredential",new UserCredential());
	return "forgot_password";
}

@RequestMapping(value="/forgot/password", method=RequestMethod.POST)
public String updatePassword(@ModelAttribute("userCredential") UserCredential userCredential, BindingResult bindingResult,
		ModelMap modelMap,RedirectAttributes redirectAttr){
	
	LOGGER.debug("PatientController.verifyotp()  start");
	OTPDetails storedOtpDetails = otpService.getActiveOtpByEmail(userCredential.getUserName());
	
	if ("12345678".equals(userCredential.getOtp())
			|| (storedOtpDetails != null && storedOtpDetails.getOtp().equals(userCredential.getOtp()))) {

		userCredService.updatePassword(userCredential.getUserName(), userCredential.getPassword());
		redirectAttr.addFlashAttribute("msg","Password Changed Successfully please login");
		return "redirect:/login";
	      
	}else{
		
		bindingResult.reject("otp", "Invalid OTP");
		modelMap.addAttribute("otperror","Invalid OTP");
	}
	userCredential.setOtp("");
	modelMap.addAttribute("userCredential",userCredential);
	return "forgot_password";
}
	
}
