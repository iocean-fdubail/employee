package fr.iocean.dta.employee.repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import fr.iocean.dta.employee.model.Employee;


public class EmployeeMockRepository implements EmployeeRepository{

	private List<Employee> employees;

	public void init() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		employees = new ArrayList<>() ;
		employees.add(new Employee(1L, "John", "Doe", new BigDecimal(30000), "123456789", sdf.parse("2016/01/01")));
		employees.add(new Employee(2L, "Mickael", "Scofield", new BigDecimal(50000), "145236985", sdf.parse("2015/06/01")));
		employees.add(new Employee(3L, "Jean", "Dupont", new BigDecimal(30000), "147852369", sdf.parse("2015/01/01")));
		employees.add(new Employee(4L, "Benjamin", "Durand", new BigDecimal(30000), "145147785", sdf.parse("2014/01/01")));
	}

	@Override
	public void saveEmployee(Employee employee) {
		employees.add(employee);

	}

	@Override
	public List<Employee> findAllEmployees() {
		return employees;
	}

	@Override
	public Employee findBySsn(String ssn) {
		Optional<Employee> result = employees.stream().filter(e -> ssn.equals(e.getSsn())).findFirst();
		if (result.isPresent()){
			return result.get();
		} else {
			return null;}
	}

	@Override
	public void updateEmployee(Employee employee) {
		Optional<Employee> employeeToUpdate = employees.stream().filter(
				e -> employee.getId() == e.getId()).findFirst();
		if (employeeToUpdate.isPresent()) {
			employeeToUpdate.get().setFirstName(employee.getFirstName());
			employeeToUpdate.get().setLastName(employee.getLastName());
			employeeToUpdate.get().setSalary(employee.getSalary());
			employeeToUpdate.get().setHireDate(employee.getHireDate());
			employeeToUpdate.get().setSsn(employee.getSsn());
		}	
	}

}
