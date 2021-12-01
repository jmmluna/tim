package es.jmmluna.tim.application.service.customer;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;
import es.jmmluna.tim.domain.model.customer.validation.InvalidDNIException;

public class CreateCustomerService implements ApplicationService {
	
	private CustomerRepository repository;
	
	public CreateCustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute() throws InvalidDNIException {
		Customer customer = new Customer(CustomerId.create("30"));
		customer.setName("José María");
		this.repository.add(customer);
		
	}

	

}
