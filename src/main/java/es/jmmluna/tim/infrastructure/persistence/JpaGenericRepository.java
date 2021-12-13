package es.jmmluna.tim.infrastructure.persistence;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGenericRepository<ENTITY>  extends JpaRepository<ENTITY, UUID> {

	public List<ENTITY> findByExpirationDate(Date expirationDate);

	public List<ENTITY> findByExpirationDateIsNotNull();
	
	public ENTITY findByName(String name);
	
	public long countByExpirationDate(Date expirationDate);
}