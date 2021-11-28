package es.jmmluna.tim.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	@Query(value = "select * from PERSONAS", nativeQuery = true)
//	List<Employee> getUnifiedElements();
//
//	@Query("from #{#entityName} ")
//	List<Employee> get2();

//	long countByExpirationDate(Date expirationDate);

	public List<Employee> findByExpirationDate(Date expirationDate);

	@Query("SELECT e FROM Employee e WHERE e.expirationDate is not null ")
	List<Employee> findByExpirationDateIsNotNull();

}