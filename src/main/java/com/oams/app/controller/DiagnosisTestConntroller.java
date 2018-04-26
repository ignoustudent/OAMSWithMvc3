/**
 * 
 */
package com.oams.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.Country;
import com.oams.app.entities.DiagnosticTestAppointmentDetail;
import com.oams.app.entities.Patient;
import com.oams.app.service.CountryService;
import com.oams.app.service.DiagnosticTestService;

/**
 * @author RAKESH SINGH
 *
 * Apr 23, 2018
 */
@Controller
@SessionAttributes("loggedInUser")
public class DiagnosisTestConntroller {

	@Autowired 
	private DiagnosticTestService diagnosticTestService;
	
	@Autowired
	private CountryService countryService;
	
	
	
	@RequestMapping(value="/book/diagnostictest",method=RequestMethod.GET)
	private String diagnostictestPage(ModelMap modelMap){
		
		DiagnosticTestAppointmentDetail test = new DiagnosticTestAppointmentDetail();
		Patient patient = (Patient) modelMap.get("loggedInUser");
		test.setPatient(patient);
		System.out.println("@@@@@@@@@@@@@@@@Hi this is @@@@@@@@@@@@@@@@@@@@@@@@@"+ modelMap.get("loggedInUser").getClass());
		List<Country>countries = (List<Country>) countryService.getCountries();
		modelMap.addAttribute("tests",diagnosticTestService.getAllDiagnosisTest());
		modelMap.addAttribute("countries",countries);
		modelMap.addAttribute("test",test);
		return "diagnosys_test";
	}
	
	@RequestMapping(value="/book/diagnostictest",method=RequestMethod.POST)
	public String saveTest(@Valid @ModelAttribute("diagnosticTestAppointmentDetail")DiagnosticTestAppointmentDetail diagnosticTestAppointmentDetail,BindingResult result,RedirectAttributes redirectAttribute){
		
		try{
		
		if(diagnosticTestAppointmentDetail.getTestList() != null){
			
			String tests = "";
			for(String testStr : diagnosticTestAppointmentDetail.getTestList()){
				
				tests += testStr+",";
			}
			
			diagnosticTestAppointmentDetail.setTests(tests);
		}
		diagnosticTestService.saveDiagnosticTestAppointment(diagnosticTestAppointmentDetail);
		redirectAttribute.addFlashAttribute("appointmentDetails",diagnosticTestAppointmentDetail);
		}catch(Exception e){
			
			e.printStackTrace();
			result.reject("error","Technical error occurred, please try after some time");
			return "diagnosys_test";
		}
		return "redirect:/appointment/confirmation";
	}
	
	@RequestMapping(value = "/diagnostictest/detail")
	public ModelAndView viewAppointDetails(@RequestParam Integer id){
		
		ModelAndView mv = new ModelAndView("appointment_details");
		DiagnosticTestAppointmentDetail details =diagnosticTestService.getAppointmentById(id);
		if(details == null){
			mv.addObject("error","Record Not Found !");
		    return mv;
		}
		mv.addObject("appointmentDetails",details);
		return mv;
	}
	
	@RequestMapping(value="/diagnostictest/history" ,method=RequestMethod.GET)
	public ModelAndView appointHostory(ModelMap modelMap){
		
		Patient patient = (Patient) modelMap.get("loggedInUser");
		ModelAndView modelAndView = new ModelAndView();
		List<DiagnosticTestAppointmentDetail>appointments = diagnosticTestService.getAppointmentDetailByEmail(patient.getEmail());
		modelAndView.setViewName("appointment_history");
		modelAndView.addObject("appointments",appointments);
		return modelAndView;
	}
}
