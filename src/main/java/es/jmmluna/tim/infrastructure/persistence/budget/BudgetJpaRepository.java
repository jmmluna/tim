package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface BudgetJpaRepository  extends JpaGenericRepository<BudgetEntity, UUID> {
	long countByYear(Integer year);
}