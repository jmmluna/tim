package es.jmmluna.tim.application.service.customer;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.validation.InvalidDNIException;
import es.jmmluna.tim.domainl.port.CustomerRepository;

public class CreateCustomerService implements ApplicationService {
	
	private CustomerRepository repository;
	
	public CreateCustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute() throws InvalidDNIException {
//		Customer customer = new Customer(CustomerId.of("30"));
//		customer.setName("José María");
//		this.repository.save(customer);
		
	}

	

}
