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
public class DBCustomerRepository implements CustomerRepository {

	@Autowired
	private JpaCustomerRepository customerRepository;

	@Override
	public long getActiveCount() {
		return customerRepository.countByExpirationDate(null);
	}

	@Override
	public Customer save(Customer customer) {		
		var savedEntity = customerRepository.save(JpaCustomerEntity.of(customer));
		return savedEntity.toModel();
	}

	@Override
	public Customer getById(CustomerId customerId) {
		Optional<JpaCustomerEntity> result = customerRepository.findById(customerId.getValue());
		var jpaCustomerEntity = result.get();
		return jpaCustomerEntity.toModel();
	}

	@Override
	public Customer getByName(String name) {
		var jpaEmployeeEntity = customerRepository.findByName(name);
		return jpaEmployeeEntity.toModel();
	}

	@Override
	public Customer delete(Customer customer) {
		// TODO: no modificar directamente el parámetro de entrada
		customer.setExpirationDate(new Date());
		return this.save(customer);
	}

	@Override
	public Customer delete(String uuid) {
		var customer = this.getById(CustomerId.of(uuid));
		return this.delete(customer);
	}

//	@Override
//	public Customer delete(UUID uuid) {
//		return this.delete(uuid.toString());
//	}

	@Override
	public List<Customer> getAll() {
		var jpaCustomerEntities = customerRepository.findAll();
		return toCustomerList(jpaCustomerEntities);
	}

	@Override
	public List<Customer> getActives() {
		var jpaCustomerEntities = customerRepository.findByExpirationDate(null);
		return toCustomerList(jpaCustomerEntities);

	}

	@Override
	public List<Customer> getInactives() {
		var jpaCustomerEntities = customerRepository.findByExpirationDateIsNotNull();
		return toCustomerList(jpaCustomerEntities);
	}

	private List<Customer> toCustomerList(List<JpaCustomerEntity> jpaCustomerEntities) {
		var customers = new ArrayList<Customer>();
		jpaCustomerEntities.forEach(jpaCustomerEntity -> customers.add(jpaCustomerEntity.toModel()));
		return customers;
	}

}
