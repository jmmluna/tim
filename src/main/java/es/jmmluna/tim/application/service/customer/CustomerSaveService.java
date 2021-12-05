package es.jmmluna.tim.application.service.customer;

import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerSaveService extends CustomerService {

	private CustomerRepository repository;

	public CustomerSaveService(CustomerRepository repository) {
		this.repository = repository;
	}

	public void execute(CustomerDTO customerDTO) {
		Customer customer = new Customer(CustomerId.of(customerDTO.getDni()));
		customer.setName(customerDTO.getName());
		customer.setSurnames(customerDTO.getSurnames());
		customer.setAddress(customerDTO.getAddress());
		customer.setPhone(customerDTO.getPhone());
		customer.setEmail(customerDTO.getEmail());
		this.repository.save(customer);
	}

}
