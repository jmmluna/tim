package es.jmmluna.tim.domain.model.work;

import java.util.Date;

import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.customer.CustomerId;

public class Work {
	private WorkId workId;
	private CustomerId customerId;
	private BudgetId budgetId;
	
	private String description;
	private Date expirationDate;
	private WorkStatus workStatus;

	public Work(WorkId workId) {
		this.workId = workId;
	}
	
	
}
