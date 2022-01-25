package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.domain.model.employee.hour.EmployeeHour;

@Repository
public interface EmployeeHourJpaRepository extends JpaRepository<EmployeeHourEntity, UUID> {
	
	@Query("SELECT e FROM EmployeeHourEntity e, WorkEntity w WHERE e.workId = w.uuid and w.status = :status AND w.expirationDate is null")
	public List<EmployeeHourEntity> findAllByWorkStatus(@Param("status") Integer status);
}

