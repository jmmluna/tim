package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Component
public class DBCustomerRepository implements CustomerRepository{

	@Autowired
	private JpaCustomerRepository customerRepository;
	
	@Override
	public void save(Customer customer) {
		customerRepository.save(JpaCustomerEntity.toEntity(customer));		
	}

	@Override
	public List<Customer> getAll() {
		var customers = new ArrayList<Customer>();
		var jpaCustomerEntities = customerRepository.findAll();
		for(var jpaCustomerEntity: jpaCustomerEntities) {
			customers.add(jpaCustomerEntity.toCustomer());
		}
		return customers;
	}

	@Override
	public Customer getById(CustomerId customerId) {
		Optional<JpaCustomerEntity> result = customerRepository.findById(customerId.getValue());
		var jpaCustomerEntity = result.get();
		return jpaCustomerEntity.toCustomer();
	}

	@Override
	public long getActiveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> getActives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getInactives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
