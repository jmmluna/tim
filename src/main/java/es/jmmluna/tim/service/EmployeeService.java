package es.jmmluna.tim.service;

import java.util.List;

import es.jmmluna.tim.model.Employee;

public interface EmployeeService {

	public List<Employee> getAll();
	public void save(Employee employee);
	public long getCount();
}
