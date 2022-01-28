package es.jmmluna.tim.domain.model.employee.hour;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.domain.model.CommonRepository;
import es.jmmluna.tim.domain.model.work.WorkStatus;

public interface EmployeeHourRepository extends CommonRepository<EmployeeHour, EmployeeHourId> {

	public List<EmployeeHour> findAll(WorkStatus workStatus);

	public UUID getNextIdentifier();
	
	public List<EmployeeHourSummary> getHourSummary();
	
	public List<EmployeeWorkHourSummary> getWorkHourSummary();
}
