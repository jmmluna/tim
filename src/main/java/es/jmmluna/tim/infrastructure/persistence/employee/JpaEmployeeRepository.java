package es.jmmluna.tim.infrastructure.persistence.employee;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<JpaEmployeeEntity, Long> {

	public List<JpaEmployeeEntity> findByExpirationDate(Date expirationDate);

	public List<JpaEmployeeEntity> findByExpirationDateIsNotNull();
	
	public JpaEmployeeEntity findByName(String name);
	
	public long countByExpirationDate(Date expirationDate);
}