package es.jmmluna.tim.domain.model.employee.hour;

import java.util.List;

import es.jmmluna.tim.domain.model.CommonRepository;
import es.jmmluna.tim.domain.model.work.WorkStatus;

public interface EmployeeHourRepository extends CommonRepository<EmployeeHour, EmployeeHourId> {

	public List<EmployeeHour> findAll(WorkStatus workStatus);
}
