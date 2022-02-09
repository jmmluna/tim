package es.jmmluna.tim.domain.model.work;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.domain.model.CommonRepository;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.invoice.Invoice;

public interface WorkRepository extends CommonRepository<Work, WorkId> {

    long getActiveCount();
    
    long getStatusCount(WorkStatus workStatus);

    List<Work> getActives();

    List<Work> getInactives();

    UUID getNextIdentifier();
    
    public Work findByBudget(BudgetId budgetId);

}
