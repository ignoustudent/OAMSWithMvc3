/**
 * 
 */
package com.oams.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author RAKESH SINGH
 *
 * Apr 2, 2018
 */

@Controller
public class ErrorController {
	
	
	
	
	
	@RequestMapping(value="/access/denied")
	public String accessDeniedPage(){
		
		return "access_denied";
	}
	
	
	/*

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	 
	 @Autowired
	 private UserService userService;
	 
	 
	 @RequestMapping("/users")
	 public ModelAndView getUsersPage() {
	  LOGGER.debug("Getting users page");
	  return new ModelAndView("users", "users", userService.getAllUsers());
	 }
	 
	 @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	 @RequestMapping("/user/{id}")
	    public ModelAndView getUserPage(@PathVariable Long id) {
	  LOGGER.debug("Getting user page for user={}", id);
	        return new ModelAndView("user", "user", userService.getUserById(id));
	    }
	 
	 @PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
	    public ModelAndView getUserCreatePage() {
	      LOGGER.debug("Getting user create form");
	        return new ModelAndView("user_create", "form", new User());
	    }
	 
	// @PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
	    public String handleUserCreateForm(@Valid @ModelAttribute("form") User form, BindingResult bindingResult) {
	      LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
	        if (bindingResult.hasErrors()) {
	            return "user_create";
	        }
	        try {
	            userService.create(form);
	        } catch (DataIntegrityViolationException e) {
	         LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
	            bindingResult.reject("email.exists", "Email already exists");
	            return "user_create";
	        }
	        return "redirect:/users";
	    }
*/}
