package fr.iocean.dta.employee;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {

		@Test
		public void testApp() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
}
