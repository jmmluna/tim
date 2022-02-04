package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetInvoice {

	@Autowired
	private InvoiceMapper mapper;
	@Autowired
	private InvoiceRepository repository;

	public InvoiceDTO execute(UUID uuid) {
		var invoice = this.repository.findById(InvoiceId.of(uuid));
		return mapper.toDTO(invoice);
	}
}
