package es.jmmluna.tim.application.service.employee.hour.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;

@Service
public class CreateEmployeeHour {

	@Autowired
	private EmployeeHourRepository repository;

	@Autowired
	private EmployeeHourMapper mapper;

	public EmployeeHourDTO execute(EmployeeHourDTO employeeHourDTO) {
		if (employeeHourDTO.getUuid() != null)
			throw new IdentifierNotAllowedException("Not null UUID are not allowed");

		employeeHourDTO.setUuid(repository.getNextIdentifier());
		
		var employeeHour = repository.save(mapper.toModel(employeeHourDTO));
		return mapper.toDTO(employeeHour);
	}
	
	
	
	
	    


}
