package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;
import es.jmmluna.tim.domain.model.invoice.*;
import es.jmmluna.tim.domain.model.work.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CreateInvoice {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private InvoiceMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WorkRepository workRepository;

    public InvoiceDTO execute(InvoiceDTO invoiceDTO) {
        if (invoiceDTO.getUuid() != null)
            throw new IdentifierNotAllowedException("Not null UUID are not allowed");

        invoiceDTO.setUuid(repository.getNextIdentifier());
        invoiceDTO.setDate(new Date());
        var invoice = repository.save(mapper.toModel(invoiceDTO));
        return mapper.toDTO(invoice);
    }

    public InvoiceDTO execute(WorkDTO workDTO) {
        var customerId = CustomerId.of(workDTO.getCustomerDTO().getUuid());
        var workId = WorkId.of(workDTO.getUuid());

        var invoice = new Invoice(InvoiceId.of(repository.getNextIdentifier()), workId, customerId, workDTO.getDescription(), new Date(), toInvoiceItemList(workDTO.getWorkItems()));
		var savedInvoice = repository.save(invoice);
		return mapper.toDTO(savedInvoice);

    }

    private List<InvoiceItem> toInvoiceItemList(List<WorkItemDTO> workItems) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        workItems.forEach(workItem -> {
            var invoiceItem = new InvoiceItem(InvoiceItemId.of(repository.getNextIdentifier()), workItem.getDescription(), workItem.getQuantity(), Price.of(workItem.getPrice()));
            invoiceItems.add(invoiceItem);
        });
        return invoiceItems;
    }
}
