package es.jmmluna.tim.application.service.customer;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;

public abstract class CustomerService {

	public CustomerDTO toDTO(Customer customer) {

		CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerId().getValue());
		customerDTO.setName(customer.getName());
		customerDTO.setSurnames(customer.getSurnames());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setEmail(customer.getEmail());

		return customerDTO;
	}

	public Customer toModel(CustomerDTO customerDTO) {

		Customer customer = new Customer(CustomerId.of(customerDTO.getDni()));
		customer.setName(customerDTO.getName());
		customer.setSurnames(customerDTO.getSurnames());
		customer.setAddress(customerDTO.getAddress());
		customer.setPhone(customerDTO.getPhone());
		customer.setEmail(customerDTO.getEmail());

		return customer;
	}

}
