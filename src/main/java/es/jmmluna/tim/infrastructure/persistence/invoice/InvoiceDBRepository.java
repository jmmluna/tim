package es.jmmluna.tim.infrastructure.persistence.invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.domain.model.invoice.Invoice;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import es.jmmluna.tim.domain.model.work.WorkId;

@Repository
public class InvoiceDBRepository implements InvoiceRepository {

	@Autowired
	private InvoiceEntityMapper mapper;

	@Autowired
	private InvoiceJpaRepository repository;

	@Override
	public long getActiveCount() {
		return repository.countByExpirationDate(null);
	}

	@Override
	public Invoice save(Invoice invoice) {
		var savedEntity = repository.save(mapper.toEntity(invoice));
		return mapper.toModel(savedEntity);
	}

	@Override
	public Invoice findById(InvoiceId invoiceId) {
		Optional<InvoiceEntity> result = repository.findById(invoiceId.getValue());
		var entity = result.get();
		return mapper.toModel(entity);
	}

	@Override
	public void delete(Invoice invoice) {
		invoice.deactivate();
		this.save(invoice);
	}

	@Override
	public List<Invoice> findAll() {
		var entities = repository.findAll();
		return toList(entities);
	}

	@Override
	public List<Invoice> getActives() {
		var entities = repository.findByExpirationDate(null);
		return toList(entities);
	}

	@Override
	public List<Invoice> getInactives() {
		var entities = repository.findByExpirationDateIsNotNull();
		return toList(entities);
	}

	private List<Invoice> toList(List<InvoiceEntity> invoiceEntities) {
		var invoices = new ArrayList<Invoice>();
		invoiceEntities.forEach(entity -> invoices.add(mapper.toModel(entity)));
		return invoices;
	}

	@Override
	public Integer getNextInvoiceNumber() {
		Long count = repository.countByYear(LocalDate.now().getYear());
		return count.intValue() + 1;
	}

	@Override
	public UUID getNextIdentifier() {
		return UUID.randomUUID();
	}

	@Override
	public Invoice findByWork(WorkId workId) {
		var invoiceEntity = repository.findByWorkId(workId.getValue());
		return mapper.toModel(invoiceEntity);
	}
}
