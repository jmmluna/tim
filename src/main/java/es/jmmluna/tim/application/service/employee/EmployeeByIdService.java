package es.jmmluna.tim.application.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeByIdService extends EmployeeService {

	@Autowired
	public EmployeeByIdService(EmployeeRepository repository) {
		super(repository);
	}

	public EmployeeDTO execute(Long id) {
		var employee = this.repository.getById(EmployeeId.of(id));
		return EmployeeService.toDTO(employee);
	}

}
