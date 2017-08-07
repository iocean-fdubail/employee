package fr.iocean.dta.employee.service;

import java.util.List;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.repository.EmployeeRepository;

public class EmployeeMockService implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeMockService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	/* For setter injection 
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	} 
	*/

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAllEmployees();
	}

	@Override
	public Employee findBySsn(String ssn) {
		return employeeRepository.findBySsn(ssn);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.updateEmployee(employee);
	}

	@Override
	public Employee findLastHired() {
		return employeeRepository.findLastHired();
	}


}
