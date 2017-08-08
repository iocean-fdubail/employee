package fr.iocean.dta.employee;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import fr.iocean.dta.App;
import fr.iocean.dta.company.service.CompanyService;
import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.service.EmployeeService;

public class EmployeeTest {

	@Test
	public void testApp() throws ParseException {

		System.setProperty("spring.profiles.active", "MOCK");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		Integer employeeNumber = employeeService.findAllEmployees().size();
		Long id = (long) (employeeNumber + 1);
		Employee newEmployee = new Employee(id, "Marie" + employeeNumber, "Curie", new BigDecimal(40000), "145687785", sdf.parse("2010/01/05"));
		employeeService.saveEmployee(newEmployee);
		Assert.assertEquals(employeeNumber + 1, employeeService.findAllEmployees().size());
		//...
		employeeService.findLastHired();

		CompanyService companyService = (CompanyService) context.getBean("companyService");
		Assert.assertEquals(employeeNumber + 1, companyService.getAllEmployees().size());

		try{
			employeeService.findBySsn("3453");
		} catch (Exception e){
		}

		context.close();
	}


}
