package es.jmmluna.tim.domain.model.budget;

import java.util.Date;
import java.util.List;

public class Budget {
	private BudgetId budgetId;
	//Identificador único secuencial de presupuesto
	private Long budgetUniqueIdentifier ;
	//Titulo o descripción del presupuesto
	private String description;
	private Date date;
	private Date expirationDate;
	private List<BudgetItemId> budgetItems;

	public Budget(BudgetId budgetId) {
		this.budgetId = budgetId;
	}
	
	public Double getTotal() {
		return 0D;
	}
	
	public void addBudgetItem(BudgetItem budgetItem) {
		
	}
}
