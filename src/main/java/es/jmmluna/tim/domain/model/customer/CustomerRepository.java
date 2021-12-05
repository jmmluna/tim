package es.jmmluna.tim.domain.model.customer;

import java.util.List;

public interface CustomerRepository {

	public void save(Customer customer);

	public List<Customer> findAll();

	public Customer findByCustomerId(CustomerId customerId);

}
