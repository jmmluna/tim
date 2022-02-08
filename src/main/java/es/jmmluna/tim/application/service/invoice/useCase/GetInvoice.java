package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import es.jmmluna.tim.domain.model.work.WorkId;

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
	
	public InvoiceDTO execute(WorkDTO workDTO) {
		var invoice = this.repository.findByWork(WorkId.of(workDTO.getUuid()));
		return mapper.toDTO(invoice);
	}
}
