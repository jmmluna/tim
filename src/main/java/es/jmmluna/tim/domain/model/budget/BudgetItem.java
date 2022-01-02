package es.jmmluna.tim.domain.model.budget;

import java.util.Objects;

import es.jmmluna.tim.domain.model.Price;

public class BudgetItem {	
	private BudgetItemId budgetItemId;
	private String description;
	private Integer quantity;
	private Price price;

	public BudgetItem(BudgetItemId budgetItemId, String description, Integer quantity, Price price) {
		this.budgetItemId = budgetItemId;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public BudgetItemId getBudgetItemId() {
		return budgetItemId;
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
		return Objects.hash(budgetItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BudgetItem other = (BudgetItem) obj;
		return Objects.equals(budgetItemId, other.budgetItemId);
	}


}
