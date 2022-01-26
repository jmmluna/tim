package es.jmmluna.tim.application.service.employee.hour.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;

@Service
public class UpdateEmployeeHour {

	@Autowired
	private EmployeeHourRepository repository;
	@Autowired
	private EmployeeHourMapper mapper;

	public EmployeeHourDTO execute(EmployeeHourDTO employeeHourDTO) {
		if (employeeHourDTO.getUuid() == null)
			throw new IdentifierNotAllowedException("Null UUID is not allowed");
				
		var savedEployeeHour = repository.save(mapper.toModel(employeeHourDTO));
		return mapper.toDTO(savedEployeeHour);
	}
}
