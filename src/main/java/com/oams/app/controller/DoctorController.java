/**
 * 
 */
package com.oams.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.Country;
import com.oams.app.entities.Doctor;
import com.oams.app.service.CountryService;
import com.oams.app.service.DoctorService;

/**
 * @author RAKESH SINGH
 *
 * Apr 14, 2018
 */
@Controller
@SessionAttributes({"loggedInUser","userRole"})
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/hospital/addDoctor", method=RequestMethod.GET)
	public String dactorPage(ModelMap modelMap){
		
		String userRole = (String) modelMap.get("userRole");
		  if(!userRole.equalsIgnoreCase("ADMIN"))
			  return "redirect:/access/denied";
		
		List<Country>countries = (List<Country>) countryService.getCountries();
		List<Doctor> doctorList = doctorService.getAllActiveDoctor();
		Doctor doctor = new Doctor();
		modelMap.addAttribute("doctor",doctor);
		modelMap.addAttribute("doctorList",doctorList);
		modelMap.addAttribute("countries",countries);
		return "doctor_reg_form";
	}
	
	@RequestMapping(value="/hospital/addDoctor", method=RequestMethod.POST)
	public String addDoctor(@Valid @ModelAttribute("doctor")Doctor doctor,ModelMap modelMap, RedirectAttributes redirectAttribute){
		
		try{
			
			doctorService.addDoctor(doctor);
			redirectAttribute.addFlashAttribute("msg","Doctor details has been saved successfully");
			
		}catch(Exception e){
			
			e.printStackTrace();
			redirectAttribute.addFlashAttribute("error","Technical Problem Occurred Please try after some time");
			return "doctor_reg_form";
		}
		return "redirect:/hospital/addDoctor";
	}
	
}
