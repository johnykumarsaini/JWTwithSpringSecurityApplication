package in.jk.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import in.jk.springboot.request.EmployeeRequest;
import in.jk.springboot.response.Response;
import in.jk.springboot.service.EmployeeService;


@CrossOrigin
@RestController
@RequestMapping("/adminapi")
public class AdminController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/employee/{empId}" , produces = "application/json",  method = RequestMethod.GET)
	public Response findEmployeeById(@PathVariable Integer empId) {
		
		return employeeService.findEmployeeById(empId);
		
	}
	
	
	@RequestMapping(value = "/employee" , produces = "application/json",  method = RequestMethod.POST)
	public Response addEmployee(@RequestBody EmployeeRequest employeeRequest) {
		
		return employeeService.addEmployee(employeeRequest);
		
	}
	
	


}
