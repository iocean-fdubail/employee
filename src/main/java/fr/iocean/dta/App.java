package fr.iocean.dta;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@ImportResource("classpath:database.xml")
public class App {
	
}
