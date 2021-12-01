package es.jmmluna.tim.domain.model.customer;

public interface CustomerRepository {

	public Customer ofId(CustomerId customerId);

	public void add(Customer customer);

	public CustomerId nextIdentity();

}
