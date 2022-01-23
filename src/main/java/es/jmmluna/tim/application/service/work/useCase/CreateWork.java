package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.work.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CreateWork {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private WorkMapper mapper;

    public WorkDTO execute(WorkDTO workDTO) {
        if (workDTO.getUuid() != null)
            throw new IdentifierNotAllowedException("Not null UUID are not allowed");

        workDTO.setUuid(workRepository.getNextIdentifier());
        workDTO.setDate(new Date());
        var work = workRepository.save(mapper.toModel(workDTO));
        return mapper.toDTO(work);
    }

    public WorkDTO execute(BudgetDTO budgetDTO) {
        var customerId = CustomerId.of(budgetDTO.getCustomerDTO().getUuid());
        var budgetId = BudgetId.of(budgetDTO.getUuid());
        var work = new Work(WorkId.of(workRepository.getNextIdentifier()), budgetId, customerId, budgetDTO.getDescription(), WorkStatus.INITIATED, new Date(), toWorkItemList(budgetDTO.getBudgetItems()));
		var savedWork = workRepository.save(work);
		return mapper.toDTO(savedWork);

    }

    private List<WorkItem> toWorkItemList(List<BudgetItemDTO> budgetItems) {
        var workItems = new ArrayList<WorkItem>();
        budgetItems.forEach(budgetItem -> {
            var workItem = new WorkItem(WorkItemId.of(workRepository.getNextIdentifier()), budgetItem.getDescription(), budgetItem.getQuantity(), Price.of(budgetItem.getPrice()), new Date());
            workItems.add(workItem);
        });
        return workItems;
    }
}
