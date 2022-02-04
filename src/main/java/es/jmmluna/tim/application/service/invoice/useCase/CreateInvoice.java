package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceMapper;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.CustomerRepository;
import es.jmmluna.tim.domain.model.invoice.*;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public InvoiceDTO execute(WorkDTO workDTO) {
        var invoiceId = InvoiceId.of(repository.getNextIdentifier());
        var workId = WorkId.of(workDTO.getUuid());
        var customerId = CustomerId.of(workDTO.getCustomerDTO().getUuid());
        var invoiceNumber = repository.getNextInvoiceNumber();
        var year = LocalDate.now().getYear();
        var currentDate = new Date();

        var invoice = new Invoice(invoiceId, workId, customerId, invoiceNumber, workDTO.getDescription(), currentDate, year,
                toInvoiceItemList(workDTO.getWorkItems()), 0.0, 0.0, 0.0, 0.0);
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
