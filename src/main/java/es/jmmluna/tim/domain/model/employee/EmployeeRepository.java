package es.jmmluna.tim.domain.model.employee;

import es.jmmluna.tim.domain.model.GenericRepository;

public interface EmployeeRepository extends GenericRepository <Employee, EmployeeId>{//{

//	public long getActiveCount();
//
//	public Employee save(Employee employee);	
//
//	public Employee getById(EmployeeId employeeId);
//
//	public Employee delete(Employee employee);
//	
//	public Employee delete(Long id);
//	
//	public List<Employee> getAll();
//
//	public List<Employee> getActives();
//
//	public List<Employee> getInactives();

	public Employee getByName(String name);

}
