package es.jmmluna.tim.application.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeDeletionService extends EmployeeService {

	@Autowired
	public EmployeeDeletionService(EmployeeRepository repository) {
		super(repository);
	}

	public EmployeeDTO execute(EmployeeDTO employeeDTO) {
		var employee = this.repository.delete(EmployeeService.toModel(employeeDTO));
		return EmployeeService.toDTO(employee);
	}

}
