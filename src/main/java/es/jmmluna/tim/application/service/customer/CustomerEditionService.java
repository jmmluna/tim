package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerEditionService extends CustomerService {

	@Autowired
	public CustomerEditionService(CustomerRepository repository) {
		super(repository);

	}

	public void execute(CustomerDTO customerDTO) {
		this.repository.save(CustomerService.toModel(customerDTO));
	}

}
