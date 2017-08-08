package fr.iocean.dta.employee;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import fr.iocean.dta.App;
import fr.iocean.dta.company.service.CompanyService;
import fr.iocean.dta.employee.model.Employee;
import fr.iocean.dta.employee.repository.EmployeeRepository;
import fr.iocean.dta.employee.service.EmployeeService;

public class EmployeeTest {

	@Test
	@Ignore("incompatible with testAppWithHibernate")
	public void testApp() throws ParseException {

		System.setProperty("spring.profiles.active", "JDBC");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		Integer employeeNumber = employeeService.findAllEmployees().size();
		Long id = (long) (employeeNumber + 1);
		Employee newEmployee = new Employee(id, "Marie" + employeeNumber, "Curie", new BigDecimal(40000), "145687785" + employeeNumber, sdf.parse("2010/01/05"));
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


		// Test employeeService.updateEmployees ROLLBACK when an employee doesn't exist
		Employee nonExistingEmployee = new Employee(-500L, "Marie", "Curie", new BigDecimal(40000), "-145687785", sdf.parse("2010/01/05"));
		newEmployee.setFirstName("Updated firstName");
		try{
			employeeService.updateEmployees(Arrays.asList(newEmployee, nonExistingEmployee));
		} catch(RuntimeException e){}
		Assert.assertEquals("Marie" + employeeNumber, employeeService.findBySsn("145687785" + employeeNumber).getFirstName());


		context.close();
	}
	
    @Test
    public void testAppWithHibernate() {
		System.setProperty("spring.profiles.active", "JPA");
        try {
            AbstractApplicationContext context =
                    new AnnotationConfigApplicationContext(App.class);
            
			/* Clean */
            EmployeeRepository repository = (EmployeeRepository) context.getBean("employeeRepository");
            repository.deleteAllEmployees();

            EmployeeService service = (EmployeeService) context.getBean("employeeService");
	    	 
	        /*
	         * Create Employee1
	         */
            Employee employee1 = new Employee();
            employee1.setFirstName("Han");
            employee1.setLastName("Yenn");
            employee1.setSalary(new BigDecimal(10000));
            employee1.setSsn("ssn00000001");
	 
	        /*
	         * Create Employee2
	         */
            Employee employee2 = new Employee();
            employee2.setFirstName("Dan");
            employee2.setLastName("Thomas");
            employee2.setSalary(new BigDecimal(20000));
            employee2.setSsn("ssn00000002");
	 
	        /*
	         * Persist both Employees
	         */
            service.saveEmployee(employee1);
            service.saveEmployee(employee2);
	 
	        /*
	         * Get all employees list from database
	         */
            List<Employee> employees = service.findAllEmployees();
            employees.forEach(System.out::println);
	 
            System.out.println("---");
	        /*
	         * update an employee
	         */
            Employee employee = service.findBySsn("ssn00000001");
            employee.setSalary(new BigDecimal(50000));
            service.updateEmployee(employee);
	 
	        /*
	         * Get all employees list from database
	         */
            List<Employee> employeeList = service.findAllEmployees();
            employeeList.forEach(System.out::println);
            context.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
