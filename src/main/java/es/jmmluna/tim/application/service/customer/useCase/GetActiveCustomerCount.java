package es.jmmluna.tim.application.service.customer.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class GetActiveCustomerCount {
	
	private CustomerRepository repository;

	@Autowired
	public GetActiveCustomerCount(CustomerRepository repository) {
		this.repository = repository;
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}
