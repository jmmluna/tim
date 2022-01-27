package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourSummary;

@Repository
public interface EmployeeHourJpaRepository extends JpaRepository<EmployeeHourEntity, UUID> {
	
	@Query("SELECT e FROM EmployeeHourEntity e, WorkEntity w WHERE e.workId = w.uuid and w.status = :status AND w.expirationDate is null")
	public List<EmployeeHourEntity> findAllByWorkStatus(@Param("status") Integer status);
	
	//String name, String surnames, Integer month, Integer year, Double totalPrice, Double totalHours, Integer entries
	
//	select e.name, e.surnames, MONTH(we.date) as month, YEAR(we.date) as year, SUM(we.price* we.hours) as total_price, SUM(we.hours) as total_hours, count(*) as entries
//	from tim.tim_works_employees we,
//	          tim.tim_works w ,    
//	          tim.tim_employees e
//	where we.work_uuid = w.uuid and
//	            we.employees_id = e.id and
//	            w.expiration_date is null
//	group by e.name, e.surnames, month, year
	
	
	@Query(value="SELECT e.name as name, e.surnames as surnames, MONTH(we.date) as month, YEAR(we.date) as year, round(SUM(we.price* we.hours),2) as totalPrice, SUM(we.hours) as totalHours, count(*) as entries "
			+ "from tim.tim_works_employees we,"
			+ "tim.tim_works w, "
			+ "tim.tim_employees e "
			+ "where we.work_uuid = w.uuid and "
			+ "we.employees_id = e.id and "
			+ "w.expiration_date is null "
			+ "group by e.name, e.surnames, month, year", nativeQuery = true)
	public List<EmployeeHourSummary> getHourSummary();
}

