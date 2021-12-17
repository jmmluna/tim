package es.jmmluna.tim.application.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class SaveCustomer extends CustomerService {

	@Autowired
	public SaveCustomer(CustomerRepository repository) {
		super(repository);
	}

	public CustomerDTO execute(CustomerDTO customerDTO) {
		var customer = this.repository.save(CustomerService.toModel(customerDTO));
		return CustomerService.toDTO(customer);
	}

}
