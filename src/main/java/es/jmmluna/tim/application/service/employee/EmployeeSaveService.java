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

	public EmployeeDTO execute(EmployeeDTO employeeDTO) {
		var savedEmployee = this.repository.save(EmployeeService.toModel(employeeDTO));
		return EmployeeService.toDTO(savedEmployee);
	}

}
