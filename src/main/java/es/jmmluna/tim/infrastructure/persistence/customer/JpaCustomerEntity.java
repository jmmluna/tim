package es.jmmluna.tim.infrastructure.persistence.customer;

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
		Customer customer = new Customer(CustomerId.of(this.dni));
		customer.setName(this.name);
		customer.setSurnames(this.surnames);
		customer.setPhone(this.phone);
		customer.setAddress(this.address);
		customer.setEmail(this.email);
		return customer;
	}
	
	public static JpaCustomerEntity toEntity(Customer customer) {
		JpaCustomerEntity entity = new JpaCustomerEntity();
		entity.setDni(customer.getCustomerId().getValue());
		entity.setName(customer.getName());
		entity.setSurnames(customer.getSurnames());
		entity.setAddress(customer.getAddress());
		entity.setPhone(customer.getPhone());
		entity.setEmail(customer.getEmail());
		return entity;
	}
	
}
