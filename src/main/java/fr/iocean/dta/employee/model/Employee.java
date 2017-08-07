package fr.iocean.dta.employee.model;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {

	private Long id;
	private String firstName;
	private String lastName;
	private String ssn;
	private BigDecimal salary;
	private Date hireDate;

	public Employee(Long id,
			String firstName,
			String lastName,
			BigDecimal salary,
			String ssn,
			Date hireDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.hireDate = hireDate;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}



}
