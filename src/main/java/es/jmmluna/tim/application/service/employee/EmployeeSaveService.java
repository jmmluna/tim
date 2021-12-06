package es.jmmluna.tim.application.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeSaveService extends EmployeeService {

	@Autowired
	public EmployeeSaveService(EmployeeRepository repository) {
		super(repository);
	}

	public void execute(EmployeeDTO employeeDTO) {
		this.repository.save(this.toModel(employeeDTO));
	}

}
