package es.jmmluna.tim.application.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.employee.EmployeeDTO;
import es.jmmluna.tim.application.service.employee.EmployeeService;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;
import es.jmmluna.tim.domain.model.employee.Employee;

@Service
public class CustomerListingService extends CustomerService {

	@Autowired
	public CustomerListingService(CustomerRepository repository) {
		super(repository);
	}

	public List<CustomerDTO> execute(EElementList type) {
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();

		if (type == EElementList.ALL)
			customers = this.toCustomerDTOList(this.repository.getAll());
		else if (type == EElementList.ACTIVE)
			customers = this.toCustomerDTOList(this.repository.getActives());
		else
			customers = this.toCustomerDTOList(this.repository.getInactives());

		return customers;
	}

	private List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
		var dtos = new ArrayList<CustomerDTO>();

		for (var customer : customers) {

			dtos.add(CustomerService.toDTO(customer));
		}
		return dtos;
	}

}
