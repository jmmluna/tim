package es.jmmluna.tim.application.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class CustomerListingService extends CustomerService {

	@Autowired
	public CustomerListingService(CustomerRepository repository) {
		super(repository);
	}

	public List<CustomerDTO> execute() {
		var customers = this.repository.getAll();
		var dtos = new ArrayList<CustomerDTO>();

		for (var customer : customers) {
			dtos.add(CustomerService.toDTO(customer));
		}
		return dtos;
	}
}
