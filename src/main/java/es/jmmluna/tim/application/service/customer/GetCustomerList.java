package es.jmmluna.tim.application.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Service
public class GetCustomerList extends CustomerService {

	@Autowired
	public GetCustomerList(CustomerRepository repository) {
		super(repository);
	}

	public List<CustomerDTO> execute(EElementList type) {
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();

		if (type == EElementList.ALL)
			customers = this.toCustomerDTOList(this.repository.findAll());
		else if (type == EElementList.ACTIVE)
			customers = this.toCustomerDTOList(this.repository.getActives());
		else
			customers = this.toCustomerDTOList(this.repository.getInactives());

		return customers;
	}

	private List<CustomerDTO> toCustomerDTOList(Iterable<Customer> customers) {
		var dtos = new ArrayList<CustomerDTO>();

		for (var customer : customers) {

			dtos.add(CustomerService.toDTO(customer));
		}
		return dtos;
	}

}
