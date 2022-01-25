package es.jmmluna.tim.application.service.employee.hour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.application.service.employee.EmployeeByIdService;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHour;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourId;
import es.jmmluna.tim.domain.model.work.WorkId;

@Component
public class EmployeeHourMapper {
	@Autowired
	private EmployeeByIdService getEmployee;

	@Autowired
	private GetWork getWork;

	public EmployeeHourDTO toDTO(EmployeeHour employeeHour) {
		var employeeDTO = getEmployee.execute(employeeHour.getEmployeeId().getValue());
		var workDTO = getWork.execute(employeeHour.getWorkId().getValue());
		return new EmployeeHourDTO(employeeHour.getEmployeeHourId().getValue(), employeeDTO, workDTO, employeeHour.getHours(), employeeHour.getPrice().getValue(), employeeHour.getDate());
	}

	public EmployeeHour toModel(EmployeeHourDTO employeeHourDTO) {
		return new EmployeeHour(EmployeeHourId.of(employeeHourDTO.getUuid()), EmployeeId.of(employeeHourDTO.getEmployeeDTO().getId()),
				WorkId.of(employeeHourDTO.getWorkDTO().getUuid()), employeeHourDTO.getDate(), employeeHourDTO.getHours(), Price.of(employeeHourDTO.getPrice()));
	}
}
