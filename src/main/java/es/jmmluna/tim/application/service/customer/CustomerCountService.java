package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerCountService extends CustomerService {

	@Autowired
	public CustomerCountService(CustomerRepository repository) {
		super(repository);
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}
