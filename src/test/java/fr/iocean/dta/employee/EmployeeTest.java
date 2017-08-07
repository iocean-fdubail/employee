package fr.iocean.dta.employee;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.repository.EmployeeRepository;

public class EmployeeTest {

	@Test
	public void testApp() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeRepository employeeRepository = (EmployeeRepository) context.getBean("employeeRepository");
		Assert.assertEquals(4, employeeRepository.findAllEmployees().size());
		Employee newEmployee = new Employee(5L, "Marie", "Curie", new BigDecimal(40000), "145687785", sdf.parse("2010/01/05"));
		employeeRepository.saveEmployee(newEmployee);
		Assert.assertEquals(5, employeeRepository.findAllEmployees().size());
		//...
		context.close();
	}
}
