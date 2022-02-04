package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.domain.model.budget.BudgetRepository;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetActiveInvoiceCount {
	
	private InvoiceRepository repository;

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}
