package com.oams.app.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oams.app.entities.Department;
import com.oams.app.service.DepartmentService;

/**
 * 
 */

/**
 * @author RAKESH SINGH
 *
 * Mar 31, 2018
 */

@RestController
@RequestMapping(value="/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	/*@PostMapping(value="/create")
	public Department createDept(@RequestBody Department department){
		logger.info("DepartmentController.createDept()  start");
		return departmentService.create(department);
		
	}*/
	
	@ResponseBody
	@RequestMapping(value="/hdepartment")
	public Iterable<Department> getDepartmentByHospitalId(@RequestParam Integer hospitalId){
		
		logger.info("DepartmentController.getDepartmentByHospitalId():- Getting Department bassed on hotel id");
		return departmentService.getDepartmentByHospitalId(hospitalId);
	}
	
	@ResponseBody
	@RequestMapping(value="/allDepartment",method=RequestMethod.GET)
	public List<Department> findAllDepartment(){
		
		return departmentService.findAllDepartment();
	}
}
