package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.Dni;
import lombok.Data;

@Entity()
@Table(name = "TIM_CUSTOMERS", schema = "TIM")
@Data
public class CustomerJpaEntity {
	@Id
	private UUID uuid;
	@Column(name="DNI")
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
	@Column(name="EXPIRATION_DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	public Customer toModel()  {
		Customer customer = new Customer(CustomerId.of(this.uuid.toString()));
		customer.setDni(Dni.of(dni));
		customer.setName(this.name);
		customer.setSurnames(this.surnames);
		customer.setPhone(this.phone);
		customer.setAddress(this.address);
		customer.setEmail(this.email);
		customer.setExpirationDate(this.expirationDate);
		return customer;
	}
	
	public static CustomerJpaEntity of(Customer customer) {
		CustomerJpaEntity entity = new CustomerJpaEntity();
		entity.setUuid(customer.getCustomerId().getValue());
		entity.setDni(customer.getDni().getValue());
		entity.setName(customer.getName());
		entity.setSurnames(customer.getSurnames());
		entity.setAddress(customer.getAddress());
		entity.setPhone(customer.getPhone());
		entity.setEmail(customer.getEmail());
		entity.setExpirationDate(customer.getExpirationDate());
		return entity;
	}
	
}
