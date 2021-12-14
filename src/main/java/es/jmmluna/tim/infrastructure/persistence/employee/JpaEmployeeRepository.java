package es.jmmluna.tim.infrastructure.persistence.employee;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface JpaEmployeeRepository extends JpaGenericRepository<JpaEmployeeEntity, Long> { //extends JpaRepository<JpaEmployeeEntity, Long> {

//	public List<JpaEmployeeEntity> findByExpirationDate(Date expirationDate);
//
//	public List<JpaEmployeeEntity> findByExpirationDateIsNotNull();
	
	public JpaEmployeeEntity findByName(String name);
	
//	public long countByExpirationDate(Date expirationDate);
}