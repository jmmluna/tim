package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateInvoice {

	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private InvoiceMapper mapper;

	public InvoiceDTO execute(InvoiceDTO invoiceDTO) {
		if (invoiceDTO.getUuid() == null)
			throw new IdentifierNotAllowedException("Null UUID is not allowed");
				
		var invoice = repository.save(mapper.toModel(invoiceDTO));
		return mapper.toDTO(invoice);
	}
}
