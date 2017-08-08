package fr.iocean.dta.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import fr.iocean.dta.employee.exception.EmployeeNotFoundException;

@Aspect
@Component
public class AopConfiguration {

	@Before("execution(* fr.iocean.dta.*.service.*.*(..))") // all service layers
	public void logUserServiceLayer(JoinPoint joinPoint) {
		System.out.println("[" + joinPoint.getSignature().getDeclaringTypeName() + " has been called] : enter in " + joinPoint.getSignature().getName());
		System.out.println("******");
	}	
	
	@AfterThrowing(pointcut = "this(fr.iocean.dta.employee.service.EmployeeService)", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, EmployeeNotFoundException e) {

		System.out.println("[" + joinPoint.getSignature().getDeclaringTypeName() + " layer throw ex] : enter in " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + e);
		System.out.println("******");

	}	
}
