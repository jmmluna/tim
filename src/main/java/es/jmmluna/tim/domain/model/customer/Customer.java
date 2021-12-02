package es.jmmluna.tim.domain.model.customer;

import java.util.Date;

public class Customer {
	
	private CustomerId customerId;
	private String name;
	private String surnames;
	private String address;
	private String phone;
	private Date expirationDate;
	
	public Customer(CustomerId customerId) {
		this.customerId = customerId;
	}

	public CustomerId getCustomerId() {
		return customerId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
