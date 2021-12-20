package es.jmmluna.tim.application.service.customer.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.CustomerMapper;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class SaveCustomer {
	private CustomerRepository repository;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	public SaveCustomer(CustomerRepository repository) {
		this.repository = repository;
	}

	public CustomerDTO execute(CustomerDTO customerDTO) {		
		var customer = this.repository.save(customerMapper.toModel(customerDTO));
		return customerMapper.toDTO(customer);
	}

}
