package fr.iocean.dta.employee;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.service.EmployeeService;

public class EmployeeTest {

	@Test
	public void testApp() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		Assert.assertEquals(4, employeeService.findAllEmployees().size());
		Employee newEmployee = new Employee(5L, "Marie", "Curie", new BigDecimal(40000), "145687785", sdf.parse("2010/01/05"));
		employeeService.saveEmployee(newEmployee);
		Assert.assertEquals(5, employeeService.findAllEmployees().size());
		//...
		employeeService.findLastHired();
		
		context.close();
	}
}
