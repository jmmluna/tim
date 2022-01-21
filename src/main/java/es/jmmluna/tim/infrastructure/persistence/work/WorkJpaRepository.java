package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface WorkJpaRepository extends JpaGenericRepository<WorkEntity, UUID> {
	long countByStatusAndExpirationDate(Integer statusCode, Date expirationDate);
}