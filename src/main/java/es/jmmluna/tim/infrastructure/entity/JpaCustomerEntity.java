package es.jmmluna.tim.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import lombok.Data;

@Entity()
@Table(name = "TIM_CUSTOMERS", schema = "TIM")
@Data
public class JpaCustomerEntity  {
	@Id
	private String dni;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SURNAMES")
	private String surnames;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "EMAIL")
	private String email;
	
	public Customer toCustomer()  {
		Customer customer = new Customer(CustomerId.of(dni));
		customer.setName(name);
		return customer;
	}

//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSurnames() {
//		return this.surnames;
//	}
//
//	public void setSurnames(String surnames) {
//		this.surnames = surnames;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

//	public Date getExpirationDate() {
//		return expirationDate;
//	}
//
//	public void setExpirationDate(Date expirationDate) {
//		this.expirationDate = expirationDate;
//	}

//	@Override
//	public CustomerId getCustomerId() throws InvalidDNIException {
//		return CustomerId.of(this.dni);
//	}
//
//	@Override
//	protected void setCustomerId(CustomerId customerId) {
//		this.dni = customerId.getValue();
//
//	}
}
