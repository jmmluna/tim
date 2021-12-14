package es.jmmluna.tim.infrastructure.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaGenericRepository<ENTITY, ID>  extends JpaRepository<ENTITY, ID> {

	public List<ENTITY> findByExpirationDate(Date expirationDate);

	public List<ENTITY> findByExpirationDateIsNotNull();
	
//	public ENTITY findByName(String name);
	
	public long countByExpirationDate(Date expirationDate);
}