package es.jmmluna.tim.application.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeCountService extends EmployeeService {

	@Autowired
	public EmployeeCountService(EmployeeRepository repository) {
		super(repository);
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}
