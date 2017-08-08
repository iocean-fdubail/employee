package fr.iocean.dta.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.service.EmployeeService;

@Service
public class CompanyService {

	private EmployeeService employeeService;
	
	@Autowired
	public CompanyService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeService.findAllEmployees();
	}
	
}
