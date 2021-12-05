package es.jmmluna.tim.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.employee.Employee;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Component
public class DBEmployeeRepository implements EmployeeRepository{

	@Autowired
	private JpaEmployeeRepository employeeRepository;

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getById(EmployeeId employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getActives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getInactives() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public void save(Customer customer) {
//		customerRepository.save(JpaCustomerEntity.toEntity(customer));		
//	}
//
//	@Override
//	public List<Customer> findAll() {
//		var customers = new ArrayList<Customer>();
//		var jpaCustomerEntities = customerRepository.findAll();
//		for(var jpaCustomerEntity: jpaCustomerEntities) {
//			customers.add(jpaCustomerEntity.toCustomer());
//		}
//		return customers;
//	}
//
//	@Override
//	public Customer findByCustomerId(CustomerId customerId) {
//		Optional<JpaCustomerEntity> result = customerRepository.findById(customerId.getValue());
//		var jpaCustomerEntity = result.get();
//		return jpaCustomerEntity.toCustomer();
//	}

}
