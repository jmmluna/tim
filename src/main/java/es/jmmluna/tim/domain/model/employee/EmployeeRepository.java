package es.jmmluna.tim.domain.model.employee;

import java.util.List;

public interface EmployeeRepository {

	public long getActiveCount();

	public Employee save(Employee employee);	

	public Employee getById(EmployeeId employeeId);

	public Employee delete(Employee employee);
	
	public List<Employee> getAll();

	public List<Employee> getActives();

	public List<Employee> getInactives();

	public Employee getByName(String name);

}
