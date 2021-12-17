package es.jmmluna.tim.application.service.customer;

import es.jmmluna.tim.domain.model.Dni;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;


//https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
public abstract class CustomerService {
	
	protected CustomerRepository repository;

	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public static CustomerDTO toDTO(Customer customer) {

//		CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerId().getValue());
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setUuid(customer.getCustomerId().getValue());
		customerDTO.setDni(customer.getDni().getValue());
		customerDTO.setName(customer.getName());
		customerDTO.setSurnames(customer.getSurnames());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setPhone(customer.getPhone());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setExpirationDate(customer.getExpirationDate());

		return customerDTO;
	}

	public static Customer toModel(CustomerDTO customerDTO) {

		Customer customer = new Customer(CustomerId.of(customerDTO.getUuid()));
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
