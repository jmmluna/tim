package es.jmmluna.tim.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.repository.EmployeeRepository;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();		
	}
	
	@Override
	public List<Employee> getActives() {
		return employeeRepository.findByExpirationDate(null);
	}
	
	@Override
	public List<Employee> getInactives() {
		return employeeRepository.findByExpirationDateIsNotNull();
	}

	@Override
	public void save(Employee employee) {
		 employeeRepository.save(employee);
		
	}
	
	public long getCount() {
		return employeeRepository.count();
	}

	@Override
	public Employee get(Long id) {
		return employeeRepository.getById(id);
	}

	@Override
	public void delete(Long id) {
		Employee employee = this.get(id);
		employee.setExpirationDate(new Date());
		employeeRepository.save(employee);
		
	}

}