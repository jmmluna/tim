package es.jmmluna.tim.domain.model.work;

import java.util.Objects;

import es.jmmluna.tim.domain.model.Price;

public class WorkItem {	
	private WorkItemId workItemId;
	private String description;
	private Integer quantity;
	private Price price;

	public WorkItem(WorkItemId workItemId, String description, Integer quantity, Price price) {
		this.workItemId = workItemId;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public WorkItemId getWorkItemId() {
		return workItemId;
	}

	public String getDescription() {
		return description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Price getPrice() {
		return price;
	}
	
	public Price getCost() {
        return getPrice()
            .multipliedBy(quantity);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(workItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkItem other = (WorkItem) obj;
		return Objects.equals(workItemId, other.workItemId);
	}


}
