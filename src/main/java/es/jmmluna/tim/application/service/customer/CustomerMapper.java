package es.jmmluna.tim.application.service.customer;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Dni;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;

@Component
public class CustomerMapper {

	public CustomerDTO toDTO(Customer customer) {

		return new CustomerDTO(customer.getCustomerId().getValue(), customer.getDni().getValue(), customer.getName(),
				customer.getSurnames(), customer.getAddress(), customer.getPhone(), customer.getEmail(), customer.getExpirationDate());

	}

	public Customer toModel(CustomerDTO customerDTO) {

		var customer = new Customer(CustomerId.of(customerDTO.getUuid()));
		customer.setDni(Dni.of(customerDTO.getDni()));
		customer.setName(customerDTO.getName());
		customer.setSurnames(customerDTO.getSurnames());
		customer.setAddress(customerDTO.getAddress());
		customer.setPhone(customerDTO.getPhone());
		customer.setEmail(customerDTO.getEmail());
		customer.setExpirationDate(customerDTO.getExpirationDate());
		return customer;
	}

}
