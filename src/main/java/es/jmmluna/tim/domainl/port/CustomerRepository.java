package es.jmmluna.tim.domainl.port;

import java.util.List;

import es.jmmluna.tim.domain.model.customer.Customer;

public interface CustomerRepository {

	
//	Optional<Customer> findById(CustomerId customerId);
//
	void save(Customer customer);
//
	List<Customer> findAll();

	
}
