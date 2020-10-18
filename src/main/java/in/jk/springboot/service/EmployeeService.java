package in.jk.springboot.service;

import in.jk.springboot.request.EmployeeRequest;
import in.jk.springboot.response.Response;

public interface EmployeeService {

	public Response findEmployeeById(Integer empId);
	public Response addEmployee(EmployeeRequest employeeRequest);
}
