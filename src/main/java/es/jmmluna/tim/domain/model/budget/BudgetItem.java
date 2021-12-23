package es.jmmluna.tim.domain.model.budget;

import es.jmmluna.tim.domain.model.Price;

public class BudgetItem {
	private BudgetItemId budgetItemId;
	private String description;
	private Integer amount;
	private Price price;

	public BudgetItem(BudgetItemId budgetItemId, String description, Integer amount, Price price) {
		this.budgetItemId = budgetItemId;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}
}
