package es.jmmluna.tim.domain.model.customer;

import java.util.List;

import es.jmmluna.tim.domain.model.CommonRepository;

public interface CustomerRepository extends CommonRepository<Customer, CustomerId>{

//	public Customer save(Customer customer);

//	public List<Customer> getAll();

//	public Customer getById(CustomerId customerId);

	public long getActiveCount();

//	public Customer delete(Customer customer);

//	public Customer delete(String uuid);

	public List<Customer> getActives();

	public List<Customer> getInactives();

	public Customer getByName(String name);

}
