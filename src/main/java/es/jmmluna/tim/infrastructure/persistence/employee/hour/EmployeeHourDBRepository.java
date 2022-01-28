package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.employee.hour.EmployeeHour;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourId;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourSummary;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeWorkHourSummary;
import es.jmmluna.tim.domain.model.work.WorkStatus;

@Component
public class EmployeeHourDBRepository implements EmployeeHourRepository {

	@Autowired
	private EmployeeHourJpaRepository employeeHourRepository;

	@Autowired
	private EmployeeHourEntityMapper mapper;

	@Override
	public EmployeeHour save(EmployeeHour employeeHour) {
		var savedEntity = employeeHourRepository.save(mapper.toEntity(employeeHour));
		return mapper.toModel(savedEntity);
	}

	@Override
	public EmployeeHour findById(EmployeeHourId employeeHourId) {
		Optional<EmployeeHourEntity> result = employeeHourRepository.findById(employeeHourId.getValue());
		var entity = result.get();
		return mapper.toModel(entity);
	}

	@Override
	public void delete(EmployeeHour employeeHour) {
		employeeHourRepository.delete(mapper.toEntity(employeeHour));
	}

	@Override
	public List<EmployeeHour> findAll() {
		var entities = employeeHourRepository.findAll();
		return toEmployeeHourList(entities);
	}
	
	@Override
	public List<EmployeeHour> findAll(WorkStatus workStatus) {
		var entities = employeeHourRepository.findAllByWorkStatus(WorkStatus.getCode(workStatus));
		return toEmployeeHourList(entities);
	}

	private List<EmployeeHour> toEmployeeHourList(List<EmployeeHourEntity> entities) {
		var employeeHours = new ArrayList<EmployeeHour>();
		entities.forEach(entity -> employeeHours.add(mapper.toModel(entity)));
		return employeeHours;
	}

	@Override
	public UUID getNextIdentifier() {		
		return UUID.randomUUID();
	}

	@Override
	public List<EmployeeHourSummary> getHourSummary() {
		return employeeHourRepository.getHourSummary();
	}
	
	@Override
	public List<EmployeeWorkHourSummary> getWorkHourSummary() {
		return employeeHourRepository.getWorkHourSummary();
	}
}
