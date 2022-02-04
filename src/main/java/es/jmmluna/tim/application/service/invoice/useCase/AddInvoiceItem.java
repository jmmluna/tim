package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceItemDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddInvoiceItem {

	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private InvoiceMapper mapper;

	public InvoiceDTO execute(UUID invoiceId, InvoiceItemDTO invoiceItemDTO) {
		var invoice = repository.findById(InvoiceId.of(invoiceId));
		invoice.add(mapper.toInvoiceItem(invoiceItemDTO));
		
		var savedInvoice = repository.save(invoice);
		return mapper.toDTO(savedInvoice);
	}
}
