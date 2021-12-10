package es.jmmluna.tim.domain.model.customer;

import java.util.Date;
import java.util.List;

import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.work.WorkId;

public class Customer {

	private CustomerId customerId;
	private Dni dni;

	private String name;
	private String surnames;
	private String address;
	private String phone;
	private String email;
	private Date expirationDate;
	private List<WorkId> works;
	private List<BudgetId> budgets;

	public Customer(CustomerId customerId) {
		this.customerId = customerId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public Dni getDni() {
		return dni;
	}

	public void setDni(Dni dni) {
		this.dni = dni;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void addWork() {

	}
	
	public void addBudget() {

	}

}
