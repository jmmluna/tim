package es.jmmluna.tim.application.service.employee.hour.useCase;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourMapper;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourId;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetEmployeeHour {

	@Autowired
	private EmployeeHourMapper mapper;

	@Autowired
	private EmployeeHourRepository repository;

	public EmployeeHourDTO execute(UUID uuid) {
		var employeeHour = this.repository.findById(EmployeeHourId.of(uuid));
		return mapper.toDTO(employeeHour);
	}
}
