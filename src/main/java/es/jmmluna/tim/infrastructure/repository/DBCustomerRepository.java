package es.jmmluna.tim.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domainl.port.CustomerRepository;

@Component
public class DBCustomerRepository implements CustomerRepository{

	@Autowired
	private JpaCustomerRepository customerRepository;
	
	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		
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

}
