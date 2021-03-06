package es.jmmluna.tim.domain.model.employee;

import java.util.Date;

import es.jmmluna.tim.domain.model.Hour;
import es.jmmluna.tim.domain.model.work.Work;

public class Employee {

	private EmployeeId employeeId;

	private String name;

	private String surnames;

	private Double customerHourPrice;

	private Double employeeHourPrice;

	private Date expirationDate;

	public Employee(EmployeeId employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeId getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public Double getCustomerHourPrice() {
		return customerHourPrice;
	}

	public void setCustomerHourPrice(Double customerHourPrice) {
		this.customerHourPrice = customerHourPrice;
	}

	public Double getEmployeeHourPrice() {
		return employeeHourPrice;
	}

	public void setEmployeeHourPrice(Double employeeHourPrice) {
		this.employeeHourPrice = employeeHourPrice;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void assignWorkHours (Hour hour, Work work) {

	}
	
	

	public void deactivate() {
		
	}
	
	public void activate() {
		
	}
}