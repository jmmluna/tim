package es.jmmluna.tim.application.service.work;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.application.service.budget.useCase.GetBudget;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.work.Work;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkItem;
import es.jmmluna.tim.domain.model.work.WorkItemId;
import es.jmmluna.tim.domain.model.work.WorkStatus;

@Component
public class WorkMapper {

    @Autowired
    private GetBudget getBudget;

    public WorkDTO toDTO(Work work) {
        return new WorkDTO(work.getWorkId().getValue(), work.getBudgetId().getValue(), work.getCustomerId().getValue(), work.getDescription(),
                WorkStatus.getCode(work.getStatus()), work.getDate(), work.getTotalCost().getValue(),
                toWorkItemDTOList(work.getWorkItems()), work.getExpirationDate());
    }

    public Work toModel(WorkDTO workDTO) {
        return new Work(WorkId.of(workDTO.getUuid()), BudgetId.of(workDTO.getBudgetId()),
                CustomerId.of(workDTO.getCustomerId()), workDTO.getDescription(),
                WorkStatus.of(workDTO.getWorkStatus()), workDTO.getDate(), toWorkItemList(workDTO.getWorkItems()),
                workDTO.getExpirationDate());
    }

    public WorkItem toWorkItem(WorkItemDTO workItemDTO) {
        return new WorkItem(WorkItemId.of(workItemDTO.getUuid()), workItemDTO.getDescription(),
                workItemDTO.getQuantity(), Price.of(workItemDTO.getPrice()));
    }

    private WorkItemDTO toWorkItemDTO(WorkItem workItem) {
        return new WorkItemDTO(workItem.getWorkItemId().getValue(), workItem.getDescription(), workItem.getQuantity(),
                workItem.getPrice().getValue());
    }

    private List<WorkItem> toWorkItemList(List<WorkItemDTO> workItemsDTO) {
        var workItems = new ArrayList<WorkItem>();

        workItemsDTO.forEach(budgetItemDTO -> workItems.add(toWorkItem(budgetItemDTO)));
        return workItems;
    }

    private List<WorkItemDTO> toWorkItemDTOList(List<WorkItem> workItems) {
        var workItemsDTO = new ArrayList<WorkItemDTO>();

        workItems.forEach(workItem -> workItemsDTO.add(toWorkItemDTO(workItem)));
        return workItemsDTO;
    }
}
