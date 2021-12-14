package es.jmmluna.tim.infrastructure.persistence.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.employee.Employee;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Component
public class DBEmployeeRepository implements EmployeeRepository {

	@Autowired
	private JpaEmployeeRepository employeeRepository;

	@Override
	public long getActiveCount() {
		return employeeRepository.countByExpirationDate(null);
	}

	@Override
	public Employee save(Employee employee) {
		var savedEntity = employeeRepository.save(JpaEmployeeEntity.toEntity(employee));
		return savedEntity.toEmployee();
	}

	@Override
	public Employee getById(EmployeeId employeeId) {
		Optional<JpaEmployeeEntity> result = employeeRepository.findById(employeeId.getValue());
		var jpaEmployeeEntity = result.get();
		return jpaEmployeeEntity.toEmployee();
	}
	
	@Override
	public Employee getByName(String name) {
		var jpaEmployeeEntity  = employeeRepository.findByName(name);
		return jpaEmployeeEntity.toEmployee();
	}

	@Override
	public Employee delete(Employee employee) {
		employee.setExpirationDate(new Date());
		return this.save(employee);
	}

//	@Override
//	public Employee delete(EmployeeId employeeId) {
//		Optional<JpaEmployeeEntity> result = employeeRepository.findById(employeeId);
//		var jpaEmployeeEntity = result.get();
//		return this.delete(jpaEmployeeEntity.toEmployee());
//	}

	@Override
	public List<Employee> getAll() {
		var jpaEmployeeEntities = employeeRepository.findAll();
		return toEmployeeList(jpaEmployeeEntities);
	}

	@Override
	public List<Employee> getActives() {
		var jpaEmployeeEntities = employeeRepository.findByExpirationDate(null);
		return toEmployeeList(jpaEmployeeEntities);

	}

	@Override
	public List<Employee> getInactives() {		
		var jpaEmployeeEntities = employeeRepository.findByExpirationDateIsNotNull();
		return toEmployeeList(jpaEmployeeEntities);
	}

	private List<Employee> toEmployeeList(List<JpaEmployeeEntity> jpaEmployeeEntities) {
		var employees = new ArrayList<Employee>();
		for (var jpaEmployeeEntity : jpaEmployeeEntities) {
			employees.add(jpaEmployeeEntity.toEmployee());
		}
		return employees;
	}

	@Override
	public Employee delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
