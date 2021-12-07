package es.jmmluna.tim.application.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.domain.model.employee.Employee;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

@Service
public class EmployeeListingService extends EmployeeService {

	@Autowired
	public EmployeeListingService(EmployeeRepository repository) {
		super(repository);
	}

	public List<EmployeeDTO> execute(EElementList type) {
		List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

		if (type == EElementList.ALL)
			employees = this.toEmployeeDTOList(this.repository.getAll());
		else if (type == EElementList.ACTIVE)
			employees = this.toEmployeeDTOList(this.repository.getActives());
		else
			employees = this.toEmployeeDTOList(this.repository.getInactives());

		return employees;
	}

	private List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
		var dtos = new ArrayList<EmployeeDTO>();

		for (var employee : employees) {

			dtos.add(EmployeeService.toDTO(employee));
		}
		return dtos;
	}
}
