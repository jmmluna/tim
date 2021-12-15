package es.jmmluna.tim.infrastructure.persistence.employee;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface EmployeeJpaRepository extends JpaGenericRepository<EmployeeJpaEntity, Long> { //extends JpaRepository<JpaEmployeeEntity, Long> {

//	public List<JpaEmployeeEntity> findByExpirationDate(Date expirationDate);
//
//	public List<JpaEmployeeEntity> findByExpirationDateIsNotNull();
	
	public EmployeeJpaEntity findByName(String name);
	
//	public long countByExpirationDate(Date expirationDate);
}