package fr.iocean.dta.employee.repository;

import java.util.List;

import fr.iocean.dta.employee.model.Employee;


public interface EmployeeRepository {

	void saveEmployee(Employee employee);

	List<Employee> findAllEmployees();

	Employee findBySsn(String ssn);

	void updateEmployee(Employee employee);

	Employee findLastHired();

	void deleteAllEmployees();

}
