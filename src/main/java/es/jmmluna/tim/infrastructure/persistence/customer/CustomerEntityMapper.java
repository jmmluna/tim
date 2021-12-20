package es.jmmluna.tim.infrastructure.persistence.customer;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Dni;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;

@Component
public class CustomerEntityMapper {

	public Customer toModel(CustomerEntity entity) {
		Customer customer = new Customer(CustomerId.of(entity.getUuid()));
		customer.setDni(Dni.of(entity.getDni()));
		customer.setName(entity.getName());
		customer.setSurnames(entity.getSurnames());
		customer.setPhone(entity.getPhone());
		customer.setAddress(entity.getAddress());
		customer.setEmail(entity.getEmail());
		customer.setExpirationDate(entity.getExpirationDate());
		return customer;
	}

	public CustomerEntity toEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
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
