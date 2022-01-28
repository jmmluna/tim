package es.jmmluna.tim.application.service.employee.hour.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeWorkHourSummary;

@Service
public class GetEmployeeWorkHourSummary {

	@Autowired
	private EmployeeHourRepository repository;

	public List<EmployeeWorkHourSummary> execute() {
		return this.repository.getWorkHourSummary();		
	}
}
