package es.jmmluna.tim.application.service.employee;

import es.jmmluna.tim.domain.model.employee.Employee;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.EmployeeRepository;

public abstract class EmployeeService {
	
	protected EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public EmployeeDTO toDTO(Employee employee) {
		var employeeDTO = new EmployeeDTO(employee.getEmployeeId().getValue());
		employeeDTO.setName(employee.getName());
		employeeDTO.setSurnames(employee.getSurnames());
		employeeDTO.setCustomerHourPrice(employee.getCustomerHourPrice());
		employeeDTO.setEmployeeHourPrice(employee.getEmployeeHourPrice());
		employeeDTO.setExpirationDate(employee.getExpirationDate());
		return employeeDTO;
	}

	public Employee toModel(EmployeeDTO employeeDTO) {
		var employee = new Employee(EmployeeId.of(employeeDTO.getId()));
		employee.setName(employeeDTO.getName());
		employee.setSurnames(employeeDTO.getSurnames());
		employee.setCustomerHourPrice(employeeDTO.getCustomerHourPrice());
		employee.setEmployeeHourPrice(employeeDTO.getEmployeeHourPrice());	
		employee.setExpirationDate(employeeDTO.getExpirationDate());

		return employee;
	}

}
