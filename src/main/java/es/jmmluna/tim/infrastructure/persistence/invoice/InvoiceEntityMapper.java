package es.jmmluna.tim.infrastructure.persistence.invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.jmmluna.tim.domain.model.invoice.Invoice;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceItem;
import es.jmmluna.tim.domain.model.invoice.InvoiceItemId;
import es.jmmluna.tim.domain.model.work.WorkId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.infrastructure.persistence.customer.CustomerJpaRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceEntityMapper {

	@Autowired
	private CustomerJpaRepository customerRepository;

	public Invoice toModel(InvoiceEntity entity) {
		return new Invoice(InvoiceId.of(entity.getUuid()), WorkId.of(entity.getWorkId()),CustomerId.of(entity.getCustomerId()),
				entity.getInvoiceNumber(), entity.getDescription(), entity.getDate(), entity.getYear(),
				toInvoiceItemList(entity.getInvoiceItems()), entity.getExpirationDate(), entity.getDiscountRate(),
				entity.getIvaRate(), entity.getReRate(), entity.getIrpfRate());
	}

	public InvoiceEntity toEntity(Invoice model) {
		var entity = new InvoiceEntity();
		entity.setUuid(model.getInvoiceId().getValue());
		entity.setCustomerId(model.getCustomerId().getValue());
		entity.setWorkId(model.getWorkId().getValue());
		entity.setInvoiceNumber(model.getInvoiceNumber());
		entity.setDescription(model.getDescription());
		entity.setYear(model.getYear());
		entity.setDate(model.getDate());
		entity.setExpirationDate(model.getExpirationDate());
		entity.setInvoiceItems(toInvoiceItemEntityList(entity, model.getInvoiceItems()));
		entity.setDiscountRate(model.getDiscountRate());
		entity.setIvaRate(model.getIvaRate());
		entity.setReRate(model.getReRate());
		entity.setIrpfRate(model.getIrpfRate());
		return entity;
	}

	private List<InvoiceItem> toInvoiceItemList(List<InvoiceItemEntity> invoiceItemEntities) {
		var invoiceItems = new ArrayList<InvoiceItem>();

		invoiceItemEntities.forEach(entity -> invoiceItems.add(toInvoiceItem(entity)));
		return invoiceItems;
	}

	private List<InvoiceItemEntity> toInvoiceItemEntityList(InvoiceEntity invoiceEntity, List<InvoiceItem> invoiceItems) {
		var invoiceItemEntities = new ArrayList<InvoiceItemEntity>();

		invoiceItems.forEach(invoiceItem -> invoiceItemEntities.add(toInvoiceItemEntity(invoiceEntity, invoiceItem)));
		return invoiceItemEntities;
	}

	private InvoiceItem toInvoiceItem(InvoiceItemEntity entity) {
		return new InvoiceItem(InvoiceItemId.of(entity.getUuid()), entity.getDescription(), entity.getAmount(),
				Price.of(entity.getPrice()));
	}

	private InvoiceItemEntity toInvoiceItemEntity(InvoiceEntity invoiceEntity, InvoiceItem invoiceItem) {
		var invoiceItemEntity = new InvoiceItemEntity();
		invoiceItemEntity.setUuid(invoiceItem.getInvoiceItemId().getValue());
		invoiceItemEntity.setInvoiceEntity(invoiceEntity);
		invoiceItemEntity.setDescription(invoiceItem.getDescription());
		invoiceItemEntity.setPrice(invoiceItem.getPrice().getValue());
		invoiceItemEntity.setAmount(invoiceItem.getQuantity());
		return invoiceItemEntity;
	}
}
