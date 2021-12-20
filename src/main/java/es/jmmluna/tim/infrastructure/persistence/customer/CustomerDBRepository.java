package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;

@Component
public class CustomerDBRepository implements CustomerRepository {
	
	@Autowired
	private CustomerEntityMapper mapper;

	@Autowired
	private CustomerJpaRepository customerRepository;

	@Override
	public long getActiveCount() {
		return customerRepository.countByExpirationDate(null);
	}

	@Override
	public Customer save(Customer customer) {				
		var savedEntity = customerRepository.save(mapper.toEntity(customer));
		return mapper.toModel(savedEntity);
	}

	@Override
	public Customer findById(CustomerId customerId) {
		Optional<CustomerEntity> result = customerRepository.findById(customerId.getValue());
		var entity = result.get();
		return mapper.toModel(entity);
	}

	@Override
	public Customer getByName(String name) {
		var entity = customerRepository.findByName(name);
		return mapper.toModel(entity);
	}

	@Override
	public void delete(Customer customer) {
		// TODO: no modificar directamente el parámetro de entrada
		customer.setExpirationDate(new Date());
		this.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		var entities = customerRepository.findAll();
		return toList(entities);
	}

	@Override
	public List<Customer> getActives() {
		var entities = customerRepository.findByExpirationDate(null);
		return toList(entities);

	}

	@Override
	public List<Customer> getInactives() {
		var entities = customerRepository.findByExpirationDateIsNotNull();
		return toList(entities);
	}

	private List<Customer> toList(List<CustomerEntity> jpaCustomerEntities) {
		var customers = new ArrayList<Customer>();
		jpaCustomerEntities.forEach(entity -> customers.add(mapper.toModel(entity)));
		return customers;
	}

}
