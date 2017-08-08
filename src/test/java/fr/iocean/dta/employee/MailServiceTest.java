package fr.iocean.dta.employee;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import fr.iocean.dta.App;
import fr.iocean.dta.employee.service.EmployeeService;
import fr.iocean.dta.employee.service.MailService;

public class MailServiceTest {
	
	@Test 
	public void testMailService(){
		
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
        MailService mailServiceA = context.getBean(MailService.class);
        mailServiceA.setMessage("you are fired");

        MailService mailServiceB = context.getBean(MailService.class);
        mailServiceB.setMessage("your are promoted");
        
        mailServiceA.sendMail(employeeService.findBySsn("123456789"));
        mailServiceB.sendMail(employeeService.findBySsn("145236985"));
        
		
		context.close();
	}

}
