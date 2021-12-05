package es.jmmluna.tim.service;

import java.util.List;

import es.jmmluna.tim.infrastructure.persistence.repository.JpaEmployeeEntity;

public interface EmployeeService {

	public List<JpaEmployeeEntity> getAll();
	public void save(JpaEmployeeEntity employee);
	public long getCount();
	public JpaEmployeeEntity get(Long id);
	public void delete(Long id);
	List<JpaEmployeeEntity> getActives();
	List<JpaEmployeeEntity> getInactives();
}
