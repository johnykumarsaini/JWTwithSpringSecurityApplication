package in.jk.springboot.serivce.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jk.springboot.entity.Employee;
import in.jk.springboot.repository.EmployeeRespository;
import in.jk.springboot.request.EmployeeRequest;
import in.jk.springboot.response.Response;
import in.jk.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRespository employeeRespository;

	public Response findEmployeeById(Integer empId) {

		Response response = null;
		Object responseData=null;

		response = new Response();
		try {
			Employee employee = employeeRespository.getOne(empId);
			System.out.println(employee);
			responseData =employee;
			response.setResponse("200", "Request Successfull.", responseData, null);
		} catch (Exception e) {

			response.setResponse("400", "Request UnSuccessfull.", null, e.getMessage());
		}

		return response;

	}

	@Override
	public Response addEmployee(EmployeeRequest employeeRequest) {

		Response response = null;
		Employee employee = new Employee();
		employee.setEmpId(employeeRequest.getEmpId());
		employee.setName(employeeRequest.getName());
		employee.setCompany(employeeRequest.getCompany());
        response = new Response();
        
        
		try {
			Employee employeeEnity = employeeRespository.save(employee);
			System.out.println(employeeEnity);
			Map<String,Object> responseMap = new HashMap<String,Object>();
			responseMap.put("employee", employeeEnity);
			response.setResponse("200", "Request Successfull.", responseMap, null);
		} catch (Exception e) {

			response.setResponse("200", "Request Successfull.", employee, null);
		}

		return response;
	}

}
