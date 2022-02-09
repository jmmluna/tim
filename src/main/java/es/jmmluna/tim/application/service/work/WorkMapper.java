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
    
    @Autowired
    private GetCustomer getCustomer;

    public WorkDTO toDTO(Work work) {
    	if(work == null)
			return null;
    	
    	var budgetDTO = getBudget.execute( work.getBudgetId().getValue());
    	var customerDTO = getCustomer.execute( work.getCustomerId().getValue());
    	
        return new WorkDTO(work.getWorkId().getValue(), budgetDTO , customerDTO, work.getDescription(),
                WorkStatus.getCode(work.getStatus()), work.getDate(), work.getTotalCost().getValue(),
                toWorkItemDTOList(work.getWorkItems()), work.getExpirationDate());
    }

    public Work toModel(WorkDTO workDTO) {
        return new Work(WorkId.of(workDTO.getUuid()), BudgetId.of(workDTO.getBudgetDTO().getUuid()),
                CustomerId.of(workDTO.getCustomerDTO().getUuid()), workDTO.getDescription(),
                WorkStatus.of(workDTO.getWorkStatus()), workDTO.getDate(), toWorkItemList(workDTO.getWorkItems()),
                workDTO.getExpirationDate());
    }

    public WorkItem toWorkItem(WorkItemDTO workItemDTO) {
        return new WorkItem(WorkItemId.of(workItemDTO.getUuid()), workItemDTO.getDescription(),
                workItemDTO.getQuantity(), Price.of(workItemDTO.getPrice()), workItemDTO.getDate());
    }

    private WorkItemDTO toWorkItemDTO(WorkItem workItem) {
        return new WorkItemDTO(workItem.getWorkItemId().getValue(), workItem.getDescription(), workItem.getQuantity(),
                workItem.getPrice().getValue(), workItem.getDate());
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
