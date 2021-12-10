package es.jmmluna.tim.domain.model.budget;

import java.util.Date;

public class Budget {
	private BudgetId budgetId;
	private String description;
	private Date expirationDate;

	public Budget(BudgetId budgetId) {
		this.budgetId = budgetId;
	}
}
