package es.jmmluna.tim.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.infrastructure.persistence.employee.JpaEmployeeEntity;
import es.jmmluna.tim.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
//	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<JpaEmployeeEntity> getAll() {
		return employeeRepository.findAll();		
	}
	
	@Override
	public List<JpaEmployeeEntity> getActives() {
//		log.info("get activos");
		return employeeRepository.findByExpirationDate(null);
	}
	
	@Override
	public List<JpaEmployeeEntity> getInactives() {
		return employeeRepository.findByExpirationDateIsNotNull();
	}

	@Override
	public void save(JpaEmployeeEntity employee) {
		 employeeRepository.save(employee);
		
	}
	
	public long getCount() {
		return employeeRepository.count();
	}

	@Override
	public JpaEmployeeEntity get(Long id) {
		return employeeRepository.getById(id);
	}

	@Override
	public void delete(Long id) {
		JpaEmployeeEntity employee = this.get(id);
		employee.setExpirationDate(new Date());
		employeeRepository.save(employee);
		
	}

}