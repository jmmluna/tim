package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface JpaCustomerRepository  extends JpaGenericRepository<JpaCustomerEntity, UUID> { //extends JpaRepository<JpaCustomerEntity, UUID> {

//	public List<JpaCustomerEntity> findByExpirationDate(Date expirationDate);
//
//	public List<JpaCustomerEntity> findByExpirationDateIsNotNull();
//	
	public JpaCustomerEntity findByName(String name);
//	
//	public long countByExpirationDate(Date expirationDate);
}