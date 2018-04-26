/**
 * 
 */
package com.oams.app.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.Country;
import com.oams.app.entities.Patient;
import com.oams.app.service.AppointmentService;
import com.oams.app.service.CountryService;

/**
 * @author RAKESH SINGH
 *
 * Apr 9, 2018
 */

@Controller
@SessionAttributes("loggedInUser")
public class BookAppointmentController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private CountryService countryService;
	
	
	
	@RequestMapping(value="/bookappointment",method=RequestMethod.GET)
	public ModelAndView viewHospitalAndDeptpage(ModelMap modelMap){
		
		LOGGER.info("BookAppointmentController.viewHospitalAndDeptpage() Getting Hospital and Department Selection Page");
		Patient patient = (Patient) modelMap.get("loggedInUser");
		modelMap.addAttribute("loggedInUser",patient);
		AppointmentDetail appDetails = new AppointmentDetail();
		appDetails.setFirstName(patient.getFirstName());
		appDetails.setLastName(patient.getLastName());
		appDetails.setEmail(patient.getEmail());
		appDetails.setMoNo(patient.getMobNo());
		
		List<Country>countries = (List<Country>) countryService.getCountries();
		ModelAndView modelAndView = new ModelAndView("appointment_booking","appointmentDetails",appDetails);
		modelAndView.addObject("countries",countries);
		
		
		return modelAndView;
	}
	
	@RequestMapping(value="/appointment/bookappointment",method=RequestMethod.POST)
	public String sameAppointment(@Valid @ModelAttribute("appointmentDetail")AppointmentDetail appointmentDetails, BindingResult result, RedirectAttributes redirectAttr){
		AppointmentDetail details = appointmentService.bookAppointMent(appointmentDetails);
		redirectAttr.addFlashAttribute("appointmentDetails",details);
				return "redirect:/appointment/confirmation";
	}
	
	@RequestMapping(value="/appointment/confirmation")
	public String appointmentConfirmation(){
		//@ModelAttribute("appointmentDetails") final  AppointmentDetail appointmentDetail
		return "appointment_confirmation";
	}
	
	@RequestMapping(value = "/appointment/detail")
	public ModelAndView viewAppointDetails(@RequestParam Integer id){
		
		ModelAndView mv = new ModelAndView("appointment_details");
		AppointmentDetail details =appointmentService.getAppointmentById(id);
		if(details == null){
			mv.addObject("error","Record Not Found !");
		    return mv;
		}
		mv.addObject("appointmentDetails",details);
		return mv;
	}
	
	@RequestMapping(value="appointment/history" ,method=RequestMethod.GET)
	public ModelAndView appointHostory(ModelMap modelMap){
		
		Patient patient = (Patient) modelMap.get("loggedInUser");
		ModelAndView modelAndView = new ModelAndView();
		List<AppointmentDetail>appointments = appointmentService.getAppointmentDetailByEmail(patient.getEmail());
		modelAndView.setViewName("appointment_history");
		modelAndView.addObject("appointments",appointments);
		modelAndView.addObject("loggedInUser",patient);
		return modelAndView;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/appointment/availability",method=RequestMethod.GET)
	public List<LocalDate> getAvailability(@RequestParam("hospitalId")Integer hospitalId, @RequestParam("departmentId")Integer departmentId){
		
	return appointmentService.getAvailability(hospitalId,departmentId);
	}

}
