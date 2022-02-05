package es.jmmluna.tim.application.service.invoice.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;

@Service
public class DisableInvoice {

	@Autowired
	private InvoiceRepository repository;
	
	public void execute(UUID uuid) {
		var invoice = this.repository.findById(InvoiceId.of(uuid));				
		this.repository.delete(invoice);
	}
}
