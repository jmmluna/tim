package es.jmmluna.tim.application.service.employee;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDTO {

	private Long id;
	private String name;
	private String surnames;
	private Double customerHourPrice;
	private Double employeeHourPrice;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expirationDate;
	

	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
