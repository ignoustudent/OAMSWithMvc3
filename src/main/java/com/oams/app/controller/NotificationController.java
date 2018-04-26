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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oams.app.entities.Notification;
import com.oams.app.service.NotificationService;

/**
 * @author RAKESH SINGH
 *
 * Apr 11, 2018
 */
@Controller
@SessionAttributes({"loggedInUser","userRole"})
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@ResponseBody
	@RequestMapping(value="/getNotifications")
	private Iterable<Notification> getpatientNotification(@RequestParam Integer patientId){
		
		return notificationService.getNotificationByPatientId(patientId);
	}
	
	
	@RequestMapping(value="/notification/add",method=RequestMethod.GET)
	public String notificationPage(ModelMap modelMap){
		
		
		String userRole = (String) modelMap.get("userRole");
		  if(!userRole.equalsIgnoreCase("ADMIN"))
			  return "redirect:/access/denied";
		  
		List<Notification> notificationList = notificationService.getAllNotification();
		modelMap.addAttribute("notificationList",notificationList);
		modelMap.addAttribute("notification",new Notification());
		
		return "notification_details";
	}
	
	@RequestMapping(value="/notification/add",method=RequestMethod.POST)
	public String addNotiication(@Valid @ModelAttribute("notification") Notification notification, ModelMap modelMap, RedirectAttributes redirectAttribute){
		
		try{
			
			notificationService.addNotification(notification);
			redirectAttribute.addFlashAttribute("msg","Notification Saved Successfully");
		}catch(Exception e){
			
			e.printStackTrace();
			modelMap.addAttribute("error", "Oops! There is some technical problem");
			
		}
		return "redirect:/notification/add";
	}
}
