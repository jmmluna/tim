package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.employee.JpaEmployeeEntity;

@Repository
public interface JpaCustomerRepository  extends JpaRepository<JpaCustomerEntity, UUID> {

	public List<JpaCustomerEntity> findByExpirationDate(Date expirationDate);

	public List<JpaCustomerEntity> findByExpirationDateIsNotNull();
	
	public JpaCustomerEntity findByName(String name);
	
	public long countByExpirationDate(Date expirationDate);
}