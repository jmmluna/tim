package es.jmmluna.tim.application.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeDeletionByIdService extends EmployeeService {

	@Autowired
	public EmployeeDeletionByIdService(EmployeeRepository repository) {
		super(repository);
	}

	public EmployeeDTO execute(Long id) {
		var employee = this.repository.delete(id);
		return EmployeeService.toDTO(employee);
	}

}
