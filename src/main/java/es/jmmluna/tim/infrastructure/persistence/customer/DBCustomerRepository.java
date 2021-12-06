package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public List<Customer> findAll() {
		var customers = new ArrayList<Customer>();
		var jpaCustomerEntities = customerRepository.findAll();
		for(var jpaCustomerEntity: jpaCustomerEntities) {
			customers.add(jpaCustomerEntity.toCustomer());
		}
		return customers;
	}

	@Override
	public Customer findByCustomerId(CustomerId customerId) {
		Optional<JpaCustomerEntity> result = customerRepository.findById(customerId.getValue());
		var jpaCustomerEntity = result.get();
		return jpaCustomerEntity.toCustomer();
	}

}