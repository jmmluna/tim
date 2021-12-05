package es.jmmluna.tim.domain.model.employee;

import java.util.List;

public interface EmployeeRepository {

	public long getCount();

	public void save(Employee employee);	

	public Employee getById(EmployeeId employeeId);

	public void delete(Long id);
	
	public List<Employee> getAll();

	public List<Employee> getActives();

	public List<Employee> getInactives();

}
