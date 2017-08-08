package fr.iocean.dta.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.iocean.dta.employee.exception.EmployeeNotFoundException;
import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.repository.EmployeeRepository;

@Service("employeeService")
@Profile(value = { "!WRONG" })
public class EmployeeMockService implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeMockService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
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
		Employee employee = employeeRepository.findBySsn(ssn);
		if (employee == null){
			throw new EmployeeNotFoundException();
		}
		return employee;
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
