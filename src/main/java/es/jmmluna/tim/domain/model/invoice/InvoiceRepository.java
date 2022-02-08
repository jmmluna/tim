package es.jmmluna.tim.domain.model.invoice;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.domain.model.CommonRepository;
import es.jmmluna.tim.domain.model.work.WorkId;

public interface InvoiceRepository extends CommonRepository<Invoice, InvoiceId> {

    long getActiveCount();    

    List<Invoice> getActives();

    List<Invoice> getInactives();

    UUID getNextIdentifier();
    
    public Integer getNextInvoiceNumber();
    
    public Invoice findByWork(WorkId workId);

}
