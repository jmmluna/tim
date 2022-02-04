package es.jmmluna.tim.application.service.invoice.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;

@Service
public class GetActiveInvoiceCount {
	@Autowired
	private InvoiceRepository repository;

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}
