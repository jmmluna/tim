package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.work.Work;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkItem;
import es.jmmluna.tim.domain.model.work.WorkItemId;
import es.jmmluna.tim.domain.model.work.WorkStatus;
import es.jmmluna.tim.infrastructure.persistence.budget.BudgetJpaRepository;
import es.jmmluna.tim.infrastructure.persistence.customer.CustomerJpaRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WorkEntityMapper {

	@Autowired
	private CustomerJpaRepository customerRepository;

	@Autowired
	private BudgetJpaRepository budgetRepository;

	public Work toModel(WorkEntity entity) {
// BudgetId.of(entity.getBudget().getUuid())
		return new Work(WorkId.of(entity.getUuid()), BudgetId.of(entity.getBudgetId()),
				CustomerId.of(entity.getCustomerId()), entity.getDescription(),
				WorkStatus.of(entity.getStatus()), entity.getDate(), toWorkItemList(entity.getWorkItems()),
				entity.getExpirationDate());

	}

	public WorkEntity toEntity(Work model) {
		WorkEntity entity = new WorkEntity();
		entity.setUuid(model.getWorkId().getValue());
		entity.setCustomerId(model.getCustomerId().getValue());
		entity.setBudgetId(model.getBudgetId().getValue());
		entity.setDescription(model.getDescription());
		entity.setStatus(WorkStatus.getCode(model.getStatus()));
		entity.setDate(model.getDate());
		entity.setWorkItems(toWorkItemEntityList(entity, model.getWorkItems()));
		entity.setExpirationDate(model.getExpirationDate());
		return entity;
	}

	private List<WorkItem> toWorkItemList(List<WorkItemEntity> workItemEntities) {
		var workItems = new ArrayList<WorkItem>();

		workItemEntities.forEach(entity -> workItems.add(toWorkItem(entity)));
		return workItems;
	}

	private List<WorkItemEntity> toWorkItemEntityList(WorkEntity workEntity, List<WorkItem> workItems) {
		var workItemEntities = new ArrayList<WorkItemEntity>();

		workItems.forEach(workItem -> workItemEntities.add(toWorkItemEntity(workEntity, workItem)));
		return workItemEntities;
	}

	private WorkItem toWorkItem(WorkItemEntity entity) {
		return new WorkItem(WorkItemId.of(entity.getUuid()), entity.getDescription(), entity.getAmount(),
				Price.of(entity.getPrice()));
	}

	private WorkItemEntity toWorkItemEntity(WorkEntity workEntity, WorkItem workItem) {
		var budgetItemEntity = new WorkItemEntity();
		budgetItemEntity.setUuid(workItem.getWorkItemId().getValue());
		budgetItemEntity.setWorkEntity(workEntity);
		budgetItemEntity.setDescription(workItem.getDescription());
		budgetItemEntity.setPrice(workItem.getPrice().getValue());
		budgetItemEntity.setAmount(workItem.getQuantity());
		return budgetItemEntity;

	}
}
