package es.jmmluna.tim.application.service.invoice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.invoice.Invoice;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.invoice.InvoiceItem;
import es.jmmluna.tim.domain.model.invoice.InvoiceItemId;
import es.jmmluna.tim.domain.model.work.WorkId;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvoiceMapper {

	@Autowired
	private GetCustomer getCustomer;
	@Autowired
	private GetWork getWork;
	
	public InvoiceDTO toDTO(Invoice invoice) {
//		log.info("##################################### customer id: " + invoice.getCustomerId().getValue());
		
		var customerDTO = getCustomer.execute(invoice.getCustomerId().getValue());
		var workDTO = getWork.execute(invoice.getWorkId().getValue());
		
		return new InvoiceDTO(invoice.getInvoiceId().getValue(), workDTO, customerDTO,
				invoice.getDescription(), invoice.getInvoiceNumber(), invoice.getYear(), invoice.getDate(),
				invoice.getTotalCost().getValue(), toInvoiceItemDTOList(invoice.getInvoiceItems()),
				invoice.getDiscountRate(), invoice.getIvaRate(), invoice.getReRate(), invoice.getIrpfRate(), invoice.getExpirationDate());

	}

	public Invoice toModel(InvoiceDTO invoiceDTO) {

		return new Invoice(InvoiceId.of(invoiceDTO.getUuid()), WorkId.of(invoiceDTO.getWorkDTO().getUuid()), CustomerId.of(invoiceDTO.getCustomerDTO().getUuid()),
				invoiceDTO.getInvoiceNumber(), invoiceDTO.getDescription(), invoiceDTO.getDate(), invoiceDTO.getYear(),
				toInvoiceItemList(invoiceDTO.getInvoiceItems()), invoiceDTO.getExpirationDate(), invoiceDTO.getDiscountRate(), invoiceDTO.getIvaRate(), invoiceDTO.getReRate(), invoiceDTO.getIrpfRate());
	}

	public InvoiceItem toInvoiceItem(InvoiceItemDTO invoiceItemDTO) {
		return new InvoiceItem(InvoiceItemId.of(invoiceItemDTO.getUuid()), invoiceItemDTO.getDescription(),
				invoiceItemDTO.getQuantity(), Price.of(invoiceItemDTO.getPrice()));
	}

	private InvoiceItemDTO toInvoiceItemDTO(InvoiceItem invoiceItem) {
		return new InvoiceItemDTO(invoiceItem.getInvoiceItemId().getValue(), invoiceItem.getDescription(),
				invoiceItem.getQuantity(), invoiceItem.getPrice().getValue());
	}

	private List<InvoiceItem> toInvoiceItemList(List<InvoiceItemDTO> invoiceItemsDTO) {
		var invoiceItems = new ArrayList<InvoiceItem>();

		invoiceItemsDTO.forEach(invoiceItemDTO -> invoiceItems.add(toInvoiceItem(invoiceItemDTO)));
		return invoiceItems;
	}

	private List<InvoiceItemDTO> toInvoiceItemDTOList(List<InvoiceItem> invoiceItems) {
		var invoiceItemsDTO = new ArrayList<InvoiceItemDTO>();

		invoiceItems.forEach(invoiceItem -> invoiceItemsDTO.add(toInvoiceItemDTO(invoiceItem)));
		return invoiceItemsDTO;
	}
}
