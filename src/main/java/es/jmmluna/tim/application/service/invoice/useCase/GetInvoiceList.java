package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.domain.model.invoice.Invoice;
import es.jmmluna.tim.domain.model.invoice.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetInvoiceList {

	@Autowired
	private InvoiceRepository repository;
	@Autowired
	private InvoiceMapper mapper;

	public List<InvoiceDTO> execute(EElementList type) {
		List<InvoiceDTO> invoice = new ArrayList<>();

		if (type == EElementList.ALL)
			invoice = this.toInvoiceDTOList(this.repository.findAll());
		else if (type == EElementList.ACTIVE)
			invoice = this.toInvoiceDTOList(this.repository.getActives());
		else
			invoice = this.toInvoiceDTOList(this.repository.getInactives());

		return invoice;
	}

	private List<InvoiceDTO> toInvoiceDTOList(Iterable<Invoice> invoices) {
		var dtos = new ArrayList<InvoiceDTO>();

		invoices.forEach(invoice -> dtos.add(mapper.toDTO(invoice)));
		return dtos;
	}
}
