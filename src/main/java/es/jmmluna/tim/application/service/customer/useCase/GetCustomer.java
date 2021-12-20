package es.jmmluna.tim.application.service.customer.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.CustomerMapper;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class GetCustomer {

	@Autowired
	private CustomerMapper mapper;
	
	private CustomerRepository repository;

	@Autowired
	public GetCustomer(CustomerRepository repository) {
		this.repository = repository;
	}

	public CustomerDTO execute(UUID uuid) {
		var customer = this.repository.findById(CustomerId.of(uuid));
		return mapper.toDTO(customer);

	}

}
