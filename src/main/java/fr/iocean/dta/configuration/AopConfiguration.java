package fr.iocean.dta.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfiguration {

	@Before("execution(* fr.iocean.dta.*.service.*.*(..))") // all service layers
//	@Before("this(fr.iocean.dta.employee.service.EmployeeService)")
	public void logUserServiceLayer(JoinPoint joinPoint) {
		System.out.println("[" + joinPoint.getSignature().getDeclaringTypeName() + " has been called] : enter in " + joinPoint.getSignature().getName());
		System.out.println("******");
	}	
}
