package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeHourJpaRepository extends JpaGenericRepository<EmployeeHourEntity, UUID> {
}