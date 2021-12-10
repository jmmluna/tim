package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerDeletionByIdService extends CustomerService {

	@Autowired
	public CustomerDeletionByIdService(CustomerRepository repository) {
		super(repository);
	}

	public CustomerDTO execute(String uuid) {
		var customer = this.repository.delete(uuid);
		return CustomerService.toDTO(customer);
	}

}
