package es.jmmluna.tim.application.service.customer;

import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerEditionService extends CustomerService {

	private CustomerRepository customerRepository;

	public CustomerEditionService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void execute(CustomerDTO customerDTO) {
		
		this.customerRepository.save(this.toModel(customerDTO));
	}

}
