package es.jmmluna.tim.application.service.invoice;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.invoice.Invoice;
import es.jmmluna.tim.domain.model.invoice.InvoiceId;
import es.jmmluna.tim.domain.model.work.WorkId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceMapper {

	@Autowired
	private GetCustomer getCustomer;
	@Autowired
	private GetWork getWork;
	
	public InvoiceDTO toDTO(Invoice invoice) {
		var customerDTO = getCustomer.execute(invoice.getCustomerId().getValue());
		var workDTO = getWork.execute(invoice.getWorkId().getValue());
		
		return new InvoiceDTO(invoice.getInvoiceId().getValue(), workDTO, customerDTO,
				invoice.getDescription(), invoice.getInvoiceNumber(), invoice.getYear(), invoice.getDate(),
				invoice.getTotalCost().getValue(), toInvoiceItemDTOList(invoice.getInvoiceItems()),
				invoice.getDiscountRate(), invoice.getIvaRate(), invoice.getReRate(), invoice.getIrpfRate(), invoice.getExpirationDate());

	}

	public Invoice toModel(InvoiceDTO invoiceDTO) {

		return new Invoice(InvoiceId.of(invoiceDTO.getUuid()), WorkId.of(invoiceDTO.getWorkDTO().getUuid()), CustomerId.of(invoiceDTO.getCustomerDTO().getUuid()),
				invoiceDTO.getInvoiceNumber(), invoiceDTO.getDescription(), invoiceDTO.getYear(), invoiceDTO.getDate(),
				toInvoiceItemList(invoiceDTO.getInvoiceItems()), invoiceDTO.getExpirationDate(), invoiceDTO.getDiscountRate(), invoiceDTO.getIvaRate(), invoiceDTO.getReRate(), invoiceDTO.getIrpfRate());
	}

	public BudgetItem toBudgetItem(BudgetItemDTO budgetItemDTO) {
		return new BudgetItem(BudgetItemId.of(budgetItemDTO.getUuid()), budgetItemDTO.getDescription(),
				budgetItemDTO.getQuantity(), Price.of(budgetItemDTO.getPrice()));
	}

	private BudgetItemDTO toBudgetItemDTO(BudgetItem budgetItem) {
		return new BudgetItemDTO(budgetItem.getBudgetItemId().getValue(), budgetItem.getDescription(),
				budgetItem.getQuantity(), budgetItem.getPrice().getValue());
	}

	private List<BudgetItem> toBudgetItemList(List<BudgetItemDTO> budgetItemsDTO) {
		var budgetItems = new ArrayList<BudgetItem>();

		budgetItemsDTO.forEach(budgetItemDTO -> budgetItems.add(toBudgetItem(budgetItemDTO)));
		return budgetItems;
	}

	private List<BudgetItemDTO> toBudgetItemDTOList(List<BudgetItem> budgetItems) {
		var budgetItemsDTO = new ArrayList<BudgetItemDTO>();

		budgetItems.forEach(budgetItem -> budgetItemsDTO.add(toBudgetItemDTO(budgetItem)));
		return budgetItemsDTO;
	}
}
