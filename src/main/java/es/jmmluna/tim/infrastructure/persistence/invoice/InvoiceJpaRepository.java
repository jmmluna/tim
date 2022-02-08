package es.jmmluna.tim.infrastructure.persistence.invoice;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface InvoiceJpaRepository  extends JpaGenericRepository<InvoiceEntity, UUID> {
	long countByYear(Integer year);
	InvoiceEntity findByWorkId(UUID workId);
}