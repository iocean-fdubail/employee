package fr.iocean.dta.company.service;

import java.util.List;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.service.EmployeeService;

public class CompanyService {

	private EmployeeService employeeService;
	
	/* For constructor injection
	public CompanyService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	*/
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeService.findAllEmployees();
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}


	
}
