/**
 * 
 */
package com.oams.app.controller;

import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.Doctor;
import com.oams.app.entities.Hospital;
import com.oams.app.entities.OTPDetails;
import com.oams.app.entities.Patient;
import com.oams.app.entities.Role;
import com.oams.app.entities.Staff;
import com.oams.app.service.AppointmentService;
import com.oams.app.service.DoctorService;
import com.oams.app.service.HospitalService;
import com.oams.app.service.OTPService;
import com.oams.app.service.PatientService;
import com.oams.app.service.StaffService;
import com.oams.app.utils.SimpleOTPGenerator;

/**
 * @author RAKESH SINGH
 *
 * Apr 2, 2018
 */
@Controller
@SessionAttributes({"loggedInUser","userRole"})
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private PatientService patientService;
	
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private OTPService otpService;
	
	@RequestMapping(value="/uploadimage", method=RequestMethod.POST)
	public String upload(@RequestParam("uploadfile") MultipartFile uploadfile,ModelMap modelMap){
		
		logger.info("HomeController.upload() upload start");
		String base64Str = "";
		logger.info("uploading file :- {}",uploadfile.getOriginalFilename());
		try{
			String userRole = (String) modelMap.get("userRole");
			if(userRole.equalsIgnoreCase("ADMIN")){
				
			Staff staff = (Staff) modelMap.get("loggedInUser");
			staff.setImage(uploadfile.getBytes());
			staffService.updateStaff(staff);
			base64Str =  new String(Base64.getEncoder().encodeToString(staff.getImage()));
			modelMap.addAttribute("loggedInUser",staff);
			}else{
			Patient patient = (Patient) modelMap.get("loggedInUser");
			patient.setImage(uploadfile.getBytes());
			patientService.updatePatient(patient);
			base64Str =  new String(Base64.getEncoder().encodeToString(patient.getImage()));
			modelMap.addAttribute("loggedInUser",patient);
			}
			
		}catch(Exception e){
		
			logger.error("Error occurred while uploading file{}",e.getMessage());
		}
		
		return "redirect:/secure/dashboard";
		
	}
	
	
	 @RequestMapping(value="/secure/dashboard",method=RequestMethod.GET)
	 public String loginSuccess(ModelMap modelMap){
		 
		 User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 GrantedAuthority grantedAuthority = user.getAuthorities().iterator().next();
		 List<Hospital> hospitals = hospitalService.geAllHospital();
		 List<Doctor> doctors = doctorService.getAllRegisteredDoctor();
		 List<AppointmentDetail> appointmentList = appointmentService.getAllAppointment();
		
		 modelMap.addAttribute("hospitalCount",hospitals.size());
		 modelMap.addAttribute("doctorCount",doctors.size());
		 modelMap.addAttribute("appointmentCount",appointmentList.size());
		 modelMap.addAttribute("userRole",grantedAuthority.getAuthority());
		 if(Role.PATIENT.toString().equals(grantedAuthority.getAuthority())){
			 Patient patient = patientService.getPatientByEmail(user.getUsername());
			 if(patient.getImage() != null){
				 
				 String base64Str = Base64.getEncoder().encodeToString(patient.getImage());
				 patient.setBase64Image(base64Str);
			 }
			 modelMap.addAttribute("loggedInUser",patient);
		 }else if(Role.ADMIN.toString().equals(grantedAuthority.getAuthority())){
			 
			 Staff staff = staffService.getStaffByEmail(user.getUsername());
			 if(staff.getImage() != null){
				 
				 String base64Str = Base64.getEncoder().encodeToString(staff.getImage());
				 staff.setBase64Image(base64Str);
			 }
			 
			 if(staff.getUserCredential() != null)
			 staff.getUserCredential().setRole(Role.ADMIN);
			 modelMap.addAttribute("loggedInUser",staff);
			
		 }
		 return "secure_dashboard";
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="/otp/send", method=RequestMethod.GET)
	 public Boolean sendOtpOverMail(@RequestParam("email")String email){
	 	
		 logger.debug("Processing Patient Email Verification");
	 	String otp = SimpleOTPGenerator.generateOtp();
	 	try{

	 		logger.debug("PatientServiceImpl.create() Sending OTP on patient mail{}");
	 		
	 		boolean isUserExist =false;
	 		if(patientService.getPatientByEmail(email) != null)
	 			isUserExist = true;
	 		else if(staffService.getStaffByEmail(email) != null)
	 			isUserExist = true;
	 		
	 			if(!isUserExist)
	 			return false;
	 		
	 		String subject = otp+":- is your Verification Code";
	 		String body = "Dear Sir/Madam, Here is your OTP "+otp+", to proceed further please use this, Note this OTP is valid only for 30 minutes";
	 		otpService.sendOTPOnEmail(email, subject, body);
	 		logger.debug("PatientServiceImpl.create() OTP Sent");
	 		OTPDetails otpDetails = new OTPDetails();
	 		otpDetails.setActive(1);
	 		otpDetails.setEmail(email);
	 		otpDetails.setOtp(otp);
	 		otpService.saveOtp(otpDetails);
	 		return true;
	 		
	 	}catch(Exception  e){
	 	
	 		logger.debug("PatientServiceImpl.create() Error While sending OTP {}",e.getMessage());
	 		return false;
	 		
	 	}
	 }
	 
	
	@GetMapping("error")
	public String error() {

		try{
        	
        	User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	if(user != null){
        		return "redirect:/secure/dashboard";
        	}
        }catch(Exception e){
        
        	e.printStackTrace();
        	return "redirect:/login";
        }

        return "redirect:/login";
	   /* ModelAndView mav = new ModelAndView();
	    String errorMessage= "You are not authorized for the requested data.";
	    mav.addObject("errorMsg", errorMessage);
	    mav.setViewName("403");*/
        }	
}
