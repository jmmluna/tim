package es.jmmluna.tim.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.repository.JpaEmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<JpaEmployeeEntity, Long> {

	public List<JpaEmployeeEntity> findByExpirationDate(Date expirationDate);

	List<JpaEmployeeEntity> findByExpirationDateIsNotNull();

}