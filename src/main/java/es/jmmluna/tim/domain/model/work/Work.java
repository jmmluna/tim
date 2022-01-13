package es.jmmluna.tim.domain.model.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import es.jmmluna.tim.domain.model.NotFoundItemException;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;

public class Work {
	private WorkId workId;
	private CustomerId customerId;
	private BudgetId budgetId;	
	private String description;	
	private WorkStatus workStatus;	
	private Date date;
	private final List<WorkItem> workItems;
	private Date expirationDate;
	private Price totalCost;
		
	public Work(WorkId workId, BudgetId budgetId, CustomerId customerId, String description, WorkStatus workStatus, Date date, List<WorkItem> workItems) {
		this.workId = workId;
		this.workItems = new ArrayList<>(workItems);		
		this.customerId = customerId;		
		this.description = description;		
		this.date = date;	
		this.workStatus = workStatus;
		totalCost = calculateTotalCost();
	}
	
	public Work(WorkId workId, BudgetId budgetId, CustomerId customerId, String description, WorkStatus workStatus, Date date, List<WorkItem> workItems, Date expirationDate) {
		this(workId,  budgetId,  customerId, description,  workStatus,  date,  workItems);
		this.expirationDate = expirationDate;
	}
	
	public WorkId getWorkId() {
		return workId;
	}
	
	public BudgetId getBudgetId() {
		return budgetId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public String getDescription() {
		return description;
	}
	
	public Date getDate() {
		return date;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public List<WorkItem> getWorkItems() {
		return Collections.unmodifiableList(workItems);
	}

	public Boolean isActive() {
		return expirationDate == null;
	}

	public void activate() {
		this.expirationDate = null;
	}

	public void deactivate() {
		this.expirationDate = new Date();
	}
	
	public WorkStatus getStatus() {
		return workStatus;
	}

	public Price getTotalCost() {
		return totalCost;
	}

	public void add(WorkItem workItem) {
		workItems.add(workItem);
		totalCost = totalCost.plus(workItem.getCost());
	}

	public void remove(WorkItem workItem) {
		if (workItems.remove(workItem))
			totalCost = totalCost.minus(workItem.getCost());
		else
			throw new NotFoundItemException("No se ha encontrado el elemento del trabajo");
	}

	private Price calculateTotalCost() {
		if (workItems.isEmpty())
			return Price.of(0.0);
		return workItems.stream().map(WorkItem::getCost).reduce(Price::plus).get();
	}
}
