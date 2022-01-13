package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface WorkJpaRepository  extends JpaGenericRepository<WorkEntity, UUID> {
	
}