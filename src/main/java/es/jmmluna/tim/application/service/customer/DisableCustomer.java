package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class DisableCustomer extends CustomerService {

	@Autowired
	public DisableCustomer(CustomerRepository repository) {
		super(repository);
	}

	public void execute(CustomerDTO customerDTO) {
		this.repository.delete(CustomerService.toModel(customerDTO));		
	}

}
