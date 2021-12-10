package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerByIdService extends CustomerService {

	@Autowired
	public CustomerByIdService(CustomerRepository repository) {
		super(repository);
	}

	public CustomerDTO execute(String uuid) {
		var customer = this.repository.getById(CustomerId.of(uuid));
		return CustomerService.toDTO(customer);
	}

}
