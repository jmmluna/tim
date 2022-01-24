package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeHourJpaRepository extends JpaRepository<EmployeeHourEntity, UUID> {
}