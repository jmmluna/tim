package es.jmmluna.tim.domain.model.customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository {

	public Customer save(Customer customer);

	public List<Customer> getAll();

	public Customer getById(CustomerId customerId);

	public long getActiveCount();

	public Customer delete(Customer customer);

	public Customer delete(String uuid);
	
	public Customer delete(UUID uuid);

	public List<Customer> getActives();

	public List<Customer> getInactives();

	public Customer getByName(String name);

	

}
