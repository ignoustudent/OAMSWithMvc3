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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.BookingCapacity;
import com.oams.app.entities.Country;
import com.oams.app.entities.Hospital;
import com.oams.app.entities.Staff;
import com.oams.app.service.BookingCapacityService;
import com.oams.app.service.CountryService;
import com.oams.app.service.DepartmentService;
import com.oams.app.service.HospitalService;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */


@Controller
@SessionAttributes({"loggedInUser","userRole"})
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private BookingCapacityService bookingCapacityService;
	
	@ResponseBody
	@RequestMapping(value="/cityWiseHospList", method=RequestMethod.GET)
	public Iterable<Hospital> getHotelListByCity(@RequestParam Integer cityId){
		
		return hospitalService.getHospitalListByCityId(cityId);
	}
	
	@RequestMapping(value="/hospital/register")
	public String renderHospitalForm(ModelMap modelMap){
		
		String userRole = (String) modelMap.get("userRole");
		  if(!userRole.equalsIgnoreCase("ADMIN"))
			  return "redirect:/access/denied";
		  
		Staff staff = (Staff) modelMap.get("loggedInUser");
		List<Country>countries = (List<Country>) countryService.getCountries();
		Hospital  hospital =  new Hospital();
		modelMap.addAttribute("hospital",hospital);
		modelMap.addAttribute("countries",countries);
		modelMap.addAttribute("departments",departmentService.findAllDepartment());
		modelMap.addAttribute("loggedInUser",staff);
		
		return "hospital_form";
	}
	
	@RequestMapping(value="/hospital/register",method=RequestMethod.POST)
	public String addHospital(@Valid @ModelAttribute("hospital") Hospital hospital, BindingResult bindingResult,
			ModelMap map,RedirectAttributes redirectAttributes){
		
		try{
			
			hospitalService.addHospital(hospital);
			redirectAttributes.addFlashAttribute("msg","Hospital Added Successfully");
		}catch(Exception e){
			
			e.printStackTrace();
			map.addAttribute("error","Error Occurred while adding Hospital !");
			return "hospital_form";
		}
		
		return "redirect:/hospital/register";
	}
		
@RequestMapping(value="/department/capacity",method=RequestMethod.GET)
public String hdCapacityPage(ModelMap map){
	
	map.addAttribute("bookingCapacity",new BookingCapacity());
	List<Country>countries = (List<Country>) countryService.getCountries();
	map.addAttribute("countries",countries);
	return "booking_capacity";
}

@RequestMapping(value="/department/capacity",method=RequestMethod.POST)
public String addCapacity(@Valid @ModelAttribute("bookingCapacity")BookingCapacity bookingCapacity,ModelMap map, RedirectAttributes redirectAttribute){
	
	try{
		
		bookingCapacityService.save(bookingCapacity);
		redirectAttribute.addAttribute("msg","Capacity Added Successfully");
		
	}catch(Exception e){
		
		e.printStackTrace();
		map.addAttribute("error","Some thing went wrong please try again or later");
	}
	return "redirect:/department/capacity";
}


	@RequestMapping(value="/hospital/list")
   public String hospitalList(ModelMap modelMap){
		
		String userRole = (String) modelMap.get("userRole");
		  if(!userRole.equalsIgnoreCase("ADMIN"))
			  return "redirect:/access/denied";
		  
	List<Hospital> hList =	hospitalService.geAllHospital();
		modelMap.addAttribute("hList",hList);
		return "h_list";
	}

	
@RequestMapping(value="/hospital/update")
public String updateHospital(@RequestParam("hospitalId")Integer id,ModelMap modelMap){
	
	String userRole = (String) modelMap.get("userRole");
	  if(!userRole.equalsIgnoreCase("ADMIN"))
		  return "redirect:/access/denied";
	  
	Hospital hospital  = hospitalService.getHospitalById(id);
	List<Country>countries = (List<Country>) countryService.getCountries();
	modelMap.addAttribute("countries",countries);
	modelMap.addAttribute("departments",departmentService.findAllDepartment());
	modelMap.addAttribute("hospital",hospital);
	return "update_hospital";
}

}
