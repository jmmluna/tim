package es.jmmluna.tim.application.service.customer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class DisableCustomerBasedOnIdenfier extends CustomerService {

	@Autowired
	public DisableCustomerBasedOnIdenfier(CustomerRepository repository) {
		super(repository);
	}

	public void execute(UUID uuid) {
		var customer = this.repository.findById(CustomerId.of(uuid));
		this.repository.delete(customer);		
	}

}
